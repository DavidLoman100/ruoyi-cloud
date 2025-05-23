package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.common.core.enums.error.CommonErrorEnum;
import com.ruoyi.common.core.exception.BizException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.system.domain.role.entity.RolePageQryEntity;
import com.ruoyi.system.domain.service.SysRoleDomainService;
import com.ruoyi.system.dto.role.RolePageQryDTO;
import com.ruoyi.system.infrastructure.role.repository.po.SysRolePo;
import com.ruoyi.system.service.RoleService;
import com.ruoyi.system.service.assembler.RoleAssembler;
import com.ruoyi.system.vo.role.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        String permsSql = sysRoleDomainService.getPermsSql("d", null);
        SysRolePo sysRolePo = sysRoleDomainService.getRoleByPerms(roleId, permsSql);
        if (Objects.isNull(sysRolePo)) {
            throw new BizException(CommonErrorEnum.INTERNAL_ERROR);
        }
        RoleVo roleVo = RoleAssembler.INSTANCE.toRoleVo(sysRolePo);
        return roleVo;
    }
}
