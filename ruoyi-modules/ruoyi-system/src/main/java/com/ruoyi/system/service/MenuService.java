package com.ruoyi.system.service;

import com.ruoyi.system.dto.menu.req.MenuAddDTO;
import com.ruoyi.system.dto.menu.req.MenuQryReqDTO;
import com.ruoyi.system.dto.menu.req.MenuUpdDTO;
import com.ruoyi.system.vo.menu.MenuTreeByRoleVo;
import com.ruoyi.system.vo.menu.MenuTreeVo;
import com.ruoyi.system.vo.menu.MenuVo;

import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-04-23 1:29
 */
public interface MenuService {
    List<MenuVo> qryMenu(MenuQryReqDTO reqDTO);

    MenuVo getMenuInfo(Long menuId);

    List<MenuTreeVo> qryTreeMenu(MenuQryReqDTO reqDTO);

    MenuTreeByRoleVo menuTreeByRole(Long roleId);

    Boolean addMenu(MenuAddDTO menuAddDTO);

    Boolean updMenu(MenuUpdDTO menuUpdDTO);
}
