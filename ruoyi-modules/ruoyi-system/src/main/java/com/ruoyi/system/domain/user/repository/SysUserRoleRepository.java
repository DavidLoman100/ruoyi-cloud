package com.ruoyi.system.domain.user.repository;

import com.ruoyi.system.domain.role.entity.RoleAuthUserEntity;
import com.ruoyi.system.infrastructure.user.repository.po.SysUserRolePo;

import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-05-28 1:08
 */
public interface SysUserRoleRepository {


    List<SysUserRolePo> qryUserRole(List<Long> roleIds);

    Boolean delBatchUserRole(List<Long> roleIds);

    Boolean delBatchUserRole(RoleAuthUserEntity authEntity);

    Boolean addBatchUserRole(RoleAuthUserEntity authEntity);
}
