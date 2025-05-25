package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.common.core.constant.CommonConstants;
import com.ruoyi.common.core.constant.SystemConstants;
import com.ruoyi.common.core.enums.error.CommonErrorEnum;
import com.ruoyi.common.core.enums.error.UserErrorEnum;
import com.ruoyi.common.core.exception.BizException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.SysRole;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.domain.role.entity.RolePageQryEntity;
import com.ruoyi.system.domain.service.SysRoleDomainService;
import com.ruoyi.system.dto.role.req.*;
import com.ruoyi.system.infrastructure.role.repository.po.SysRolePo;
import com.ruoyi.system.service.RoleService;
import com.ruoyi.system.service.assembler.RoleAssembler;
import com.ruoyi.system.vo.role.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Set;

/**
 * @author DavidLoman
 * @create 2025-05-11 15:26
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private SysRoleDomainService sysRoleDomainService;
    @Override
    public PageListVo<RoleVo> pageQryRoleList(RolePageQryDTO reqDTO) {
        RolePageQryEntity rolePageQryEntity = RoleAssembler.INSTANCE.toRolePageQryEntity(reqDTO);
        Page<SysRolePo> sysRolePoPage = sysRoleDomainService.pageQryRoleList(rolePageQryEntity);
        PageListVo<RoleVo> roleVoPageListVo = RoleAssembler.INSTANCE.toPageListVo(sysRolePoPage);
        return roleVoPageListVo;
    }

    @Override
    public PageListVo<RoleVo> pageQryRoleListByPerms(RolePageQryDTO reqDTO) {
        RolePageQryEntity rolePageQryEntity = RoleAssembler.INSTANCE.toRolePageQryEntity(reqDTO);

        String permsSql = sysRoleDomainService.getPermsSql("d", null);
        Page<SysRolePo> sysRolePoPage = null;
        if (StringUtils.hasText(permsSql)) {
            rolePageQryEntity.setDataScopeSql(permsSql);
            sysRolePoPage = sysRoleDomainService.pageQryRoleListByPerms(rolePageQryEntity);
        } else {
            sysRolePoPage = sysRoleDomainService.pageQryRoleList(rolePageQryEntity);
        }
        PageListVo<RoleVo> roleVoPageListVo = RoleAssembler.INSTANCE.toPageListVo(sysRolePoPage);
        return roleVoPageListVo;
    }

    @Override
    public RoleVo getRoleInfo(Long roleId) {
        SysRolePo sysRolePo = checkRolePerm(roleId);
        RoleVo roleVo = RoleAssembler.INSTANCE.toRoleVo(sysRolePo);
        return roleVo;
    }

    @Override
    public Boolean addRole(RoleAddDTO roleAddDTO) {
        RolePageQryEntity qryEntity = new RolePageQryEntity();
        qryEntity.setRoleName(roleAddDTO.getRoleName());
        qryEntity.setRoleKey(roleAddDTO.getRoleKey());
        sysRoleDomainService.checkSaveRoleParam(qryEntity);

        sysRoleDomainService.saveRoleAndMenu(roleAddDTO);
        return null;
    }

    @Override
    public Boolean updRole(RoleUpdDTO roleUpdDTO) {
        checkRoleAdmin(roleUpdDTO.getRoleId());
        checkRolePerm(roleUpdDTO.getRoleId());
        RolePageQryEntity qryEntity = new RolePageQryEntity();
        qryEntity.setRoleId(roleUpdDTO.getRoleId());
        qryEntity.setRoleName(roleUpdDTO.getRoleName());
        qryEntity.setRoleKey(roleUpdDTO.getRoleKey());
        sysRoleDomainService.checkSaveRoleParam(qryEntity);
        sysRoleDomainService.updRoleAndMenu(roleUpdDTO);
        return true;
    }



    @Override
    public Boolean updRoleDataScope(RoleDataScopeDTO reqDTO) {
        checkRoleAdmin(reqDTO.getRoleId());
        checkRolePerm(reqDTO.getRoleId());
        return sysRoleDomainService.updRoleDataScope(reqDTO);
    }

    @Override
    public Boolean changeStatus(RoleStatusDTO reqDTO) {
        checkRoleAdmin(reqDTO.getRoleId());
        checkRolePerm(reqDTO.getRoleId());
        sysRoleDomainService.updRoleStatus(reqDTO);
        return true;
    }

    private SysRolePo checkRolePerm(Long roleId) {
        String permsSql = sysRoleDomainService.getPermsSql("d", null);
        SysRolePo sysRolePo = sysRoleDomainService.getRoleByPerms(roleId, permsSql);
        if (Objects.isNull(sysRolePo)) {
            throw new BizException(CommonErrorEnum.INTERNAL_ERROR);
        }
        return sysRolePo;
    }
    private void checkRoleAdmin(Long roleId) {
        if (CommonConstants.roleAdminId == roleId) {
            throw new BizException(UserErrorEnum.NO_OPT_ADMIN_ROLE);
        }
    }

}
