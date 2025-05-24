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

    SysMenuPo getMenuByPerms(String perms);

    List<Long> getMenuIdByRole(Long roleId, Boolean menuCheckStrictly);

    Boolean checkNameExist(Long menuId, String menuName);

    Boolean addMenu(SysMenuPo sysMenuPo);

    Boolean updMenu(SysMenuPo sysMenuPo);

    Boolean hasChild(Long menuId);

    Boolean deleteMenu(Long menuId);

}
