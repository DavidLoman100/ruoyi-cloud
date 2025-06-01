package com.ruoyi.system.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.constant.CommonConstants;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.enums.dict.RolePermEnum;
import com.ruoyi.common.core.enums.error.RoleErrorEnum;
import com.ruoyi.common.core.enums.error.UserErrorEnum;
import com.ruoyi.common.core.exception.BizException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.SysRole;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.common.context.PermissionContext;
import com.ruoyi.system.domain.menu.repository.SysMenuRepository;
import com.ruoyi.system.domain.role.entity.RolePageQryEntity;
import com.ruoyi.system.domain.role.repository.SysRoleDeptRepository;
import com.ruoyi.system.domain.role.repository.SysRoleMenuRepository;
import com.ruoyi.system.domain.role.repository.SysRoleRepository;
import com.ruoyi.system.domain.user.repository.SysUserRoleRepository;
import com.ruoyi.system.dto.role.req.RoleAddDTO;
import com.ruoyi.system.dto.role.req.RoleDataScopeDTO;
import com.ruoyi.system.dto.role.req.RoleStatusDTO;
import com.ruoyi.system.dto.role.req.RoleUpdDTO;
import com.ruoyi.system.infrastructure.menu.repository.po.SysMenuPo;
import com.ruoyi.system.infrastructure.role.repository.po.SysRoleDeptPo;
import com.ruoyi.system.infrastructure.role.repository.po.SysRoleMenuPo;
import com.ruoyi.system.infrastructure.role.repository.po.SysRolePo;
import com.ruoyi.system.service.assembler.RoleAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author DavidLoman
 * @create 2025-05-11 15:33
 */
@Service
public class SysRoleDomainService {

    @Autowired
    private SysRoleRepository sysRoleRepository;
    @Autowired
    private SysRoleMenuRepository sysRoleMenuRepository;
    @Autowired
    private SysMenuRepository sysMenuRepository;
    @Autowired
    private SysRoleDeptRepository sysRoleDeptRepository;
    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;


    public Page<SysRolePo> pageQryRoleList(RolePageQryEntity rolePageQryEntity) {
        return sysRoleRepository.pageQryRoleList(rolePageQryEntity);
    }


    public Page<SysRolePo> pageQryRoleListByPerms(RolePageQryEntity rolePageQryEntity) {
        return sysRoleRepository.pageQryRoleListByPerms(rolePageQryEntity);
    }

    /**
     * 根据权限拼接查询数据权限
     * @param deptAlias 部门别名
     * @param userAlias 用户别名
     */
    public String getPermsSql(String deptAlias, String userAlias) {
        LoginUser curUser = SecurityUtils.getLoginUser();
        Set<Long> roles = curUser.getSysUser().getRoles().stream().map(SysRole::getRoleId).collect(Collectors.toSet());
        Long deptId = curUser.getSysUser().getDeptId();
        if (CollectionUtils.isEmpty(roles) || Objects.isNull(deptId)) {
            throw new BizException(UserErrorEnum.ACCOUNT_ERROR);
        }

        List<SysRolePo> roleInfo = sysRoleRepository.getRoleInfo(roles);
        String permission = PermissionContext.get()[0];;
        SysMenuPo menuPo = sysMenuRepository.getMenuByPerms(permission);
        List<SysRoleMenuPo> roleMenuPos = sysRoleMenuRepository.getInfoByMenu(menuPo.getMenuId());
        Set<Long> rolePermsSet = roleMenuPos.stream().map(SysRoleMenuPo::getRoleId).collect(Collectors.toSet());
        StringBuilder sqlStr = new StringBuilder();
        Set<String> dataScopeSet = new HashSet<>();
        for (SysRolePo sysRolePo : roleInfo) {
            String dataScope = sysRolePo.getDataScope();
            List<String> scopeCustomIds = new ArrayList<>();
            if (dataScopeSet.contains(dataScope) || StringUtils.equals(sysRolePo.getStatus(), UserConstants.ROLE_DISABLE)) {
                continue;
            }
            //当前角色没有该接口的权限 直接跳过
            if (!rolePermsSet.contains(sysRolePo.getRoleId())) {
                continue;
            }
            if (RolePermEnum.DATA_SCOPE_ALL.getCode().equals(dataScope))
            {
                dataScopeSet.add(dataScope);
                break;
            }
            else if (RolePermEnum.DATA_SCOPE_CUSTOM.getCode().equals(dataScope))
            {
                scopeCustomIds.add(String.valueOf(sysRolePo.getRoleId()));
            }
            else if (RolePermEnum.DATA_SCOPE_DEPT.getCode().equals(dataScope))
            {
                sqlStr.append(StringUtils.format(" OR {}.dept_id = {} ", deptAlias, deptId));
            }
            else if (RolePermEnum.DATA_SCOPE_DEPT_AND_CHILD.getCode().equals(dataScope))
            {
                sqlStr.append(StringUtils.format(" OR {}.dept_id IN ( SELECT dept_id FROM sys_dept WHERE dept_id = {} or find_in_set( {} , ancestors ) )", deptAlias, deptId, deptId));
            }
            else if (RolePermEnum.DATA_SCOPE_SELF.getCode().equals(dataScope))
            {
                if (StringUtils.isNotBlank(userAlias)) {
                    sqlStr.append(StringUtils.format(" OR {}.user_id = {} ", userAlias, curUser.getUserid()));
                } else {
                    // 数据权限为仅本人且没有userAlias别名 避免查出所有的数据
                    sqlStr.append(StringUtils.format(" OR {}.dept_id = 0 ", deptAlias));
                }
            }

            if (!scopeCustomIds.isEmpty()) {
                if (scopeCustomIds.size() > 1)
                {
                    // 多个自定数据权限使用in查询，避免多次拼接。
                    sqlStr.append(StringUtils.format(" OR {}.dept_id IN ( SELECT dept_id FROM sys_role_dept WHERE role_id in ({}) ) ", deptAlias, String.join(",", scopeCustomIds)));
                }
                else
                {
                    sqlStr.append(StringUtils.format(" OR {}.dept_id IN ( SELECT dept_id FROM sys_role_dept WHERE role_id = {} ) ", deptAlias, sysRolePo.getRoleId()));
                }
                dataScopeSet.add(dataScope);
            }


            // 角色都不包含传递过来的权限字符，这个时候sqlString也会为空，所以要限制一下,不查询任何数据
            if (StringUtils.isEmpty(dataScopeSet))
            {
                sqlStr.append(StringUtils.format(" OR {}.dept_id = 0 ", deptAlias));
            }

            if (StringUtils.isNotBlank(sqlStr.toString()))
            {
                return "AND (" + sqlStr.toString().substring(4) + ")";
            }
        }
        return null;
    }

     public SysRolePo  getRoleByPerms(Long roleId, String dataScopeSql) {
         if (StringUtils.hasText(dataScopeSql)) {
             return sysRoleRepository.getRoleByPerms(roleId, dataScopeSql);
         } else {
             return sysRoleRepository.getRoleInfo(roleId);
         }
    }


    public Boolean checkSaveRoleParam(RolePageQryEntity entity) {
        List<SysRolePo> sysRolePos = sysRoleRepository.qryRoleList(entity);
        if (!CollectionUtils.isEmpty(sysRolePos)) {
            SysRolePo sysRolePo = sysRolePos.get(CommonConstants.zero);
            if (entity.getRoleName().equals(sysRolePo.getRoleName())) {
                throw new BizException(RoleErrorEnum.ROLE_NAME_EXIST);
            }
            if (entity.getRoleKey().equals(sysRolePo.getRoleKey())) {
                throw new BizException(RoleErrorEnum.ROLE_KEY_EXIST);
            }
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean saveRoleAndMenu(RoleAddDTO roleAddDTO) {
        SysRolePo sysRolePo = RoleAssembler.INSTANCE.toSysRolePo(roleAddDTO);
        sysRoleRepository.addRole(sysRolePo);

        if (!CollectionUtils.isEmpty(roleAddDTO.getMenuIds())) {
            List<SysRoleMenuPo> sysRoleMenuPos = new ArrayList<>();
            for (Long menuId : roleAddDTO.getMenuIds()) {
                sysRoleMenuPos.add(new SysRoleMenuPo(sysRolePo.getRoleId(), menuId));
            }
            sysRoleMenuRepository.addBatchRoleMenu(sysRoleMenuPos);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean updRoleAndMenu(RoleUpdDTO roleUpdDTO) {
        SysRolePo sysRolePo = RoleAssembler.INSTANCE.toSysRolePo(roleUpdDTO);
        sysRoleRepository.updRole(sysRolePo);

        if (sysRoleMenuRepository.hasRoleMenu(sysRolePo.getRoleId())) {
            sysRoleMenuRepository.deleteRoleMenu(sysRolePo.getRoleId());
        }
        if (!CollectionUtils.isEmpty(roleUpdDTO.getMenuIds())) {
            List<SysRoleMenuPo> sysRoleMenuPos = new ArrayList<>();
            for (Long menuId : roleUpdDTO.getMenuIds()) {
                sysRoleMenuPos.add(new SysRoleMenuPo(sysRolePo.getRoleId(), menuId));
            }
            sysRoleMenuRepository.addBatchRoleMenu(sysRoleMenuPos);
        }
        return true;
    }

    public Boolean updRoleDataScope(RoleDataScopeDTO reqDTO) {
        SysRolePo sysRolePo = RoleAssembler.INSTANCE.toSysRolePo(reqDTO);
        sysRoleRepository.updRole(sysRolePo);

        Boolean isEx = sysRoleDeptRepository.hasRoleDept(sysRolePo.getRoleId());
        if (isEx) {
            sysRoleDeptRepository.deleteRoleDept(sysRolePo.getRoleId());
        }
        if (!CollectionUtils.isEmpty(reqDTO.getDeptIds())) {
            List<SysRoleDeptPo> sysRoleDeptPos = reqDTO.getDeptIds().stream()
                    .map(deptId -> new SysRoleDeptPo(sysRolePo.getRoleId(), deptId))
                    .collect(Collectors.toList());
            sysRoleDeptRepository.addRoleDept(sysRoleDeptPos);

        }
        return true;
    }

    public void updRoleStatus(RoleStatusDTO reqDTO) {
        SysRolePo sysRolePo = RoleAssembler.INSTANCE.toSysRolePo(reqDTO);
        sysRoleRepository.updRole(sysRolePo);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean delRole(List<Long> roleIds) {
        sysRoleRepository.delRole(roleIds);
        sysUserRoleRepository.delBatchUserRole(roleIds);
        sysRoleMenuRepository.delBatchRoleMenu(roleIds);
        sysRoleDeptRepository.deleteBatchRoleDept(roleIds);
        return true;
    }
}
