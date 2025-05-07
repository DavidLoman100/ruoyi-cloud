package com.ruoyi.system.domain.role.repository;

import org.springframework.stereotype.Repository;

/**
 * @author DavidLoman
 * @create 2025-04-26 18:06
 */
public interface SysRoleMenuRepository {

    boolean hasRoleMenu(Long menuId);
}
