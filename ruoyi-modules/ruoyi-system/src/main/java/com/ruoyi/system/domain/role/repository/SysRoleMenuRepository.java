package com.ruoyi.system.domain.role.repository;

import com.ruoyi.system.infrastructure.role.repository.po.SysRoleMenuPo;

import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-04-26 18:06
 */
public interface SysRoleMenuRepository {

    boolean hasRoleMenu(Long menuId);

    List<SysRoleMenuPo> getInfoByMenu(Long menuId);

    boolean addBatchRoleMenu(List<SysRoleMenuPo> sysRoleMenuPos);

    boolean deleteRoleMenu(Long roleId);
}
