package com.ruoyi.system.domain.role.repository;

import com.ruoyi.system.infrastructure.role.repository.po.SysRolePo;

/**
 * @author DavidLoman
 * @create 2025-04-26 18:06
 */
public interface SysRoleRepository {
    SysRolePo getRoleInfo(Long roleId);
}
