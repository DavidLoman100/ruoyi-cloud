package com.ruoyi.system.domain.menu.repository;

import com.ruoyi.system.domain.menu.entity.MenuQryEntity;
import com.ruoyi.system.infrastructure.menu.repository.po.SysMenuPo;

import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-05-01 1:01
 */
public interface SysMenuRepository {
    List<SysMenuPo> qryMenu(MenuQryEntity qryEntity);

    List<SysMenuPo> qryMenuByPermission(MenuQryEntity qryEntity);

    SysMenuPo getMenuById(Long menuId);

    List<Long> getMenuIdByRole(Long roleId, Integer menuCheckStrictly);
}
