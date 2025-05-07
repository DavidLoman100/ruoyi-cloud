package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.service.SysMenuDomainService;
import com.ruoyi.system.dto.menu.req.MenuAddDTO;
import com.ruoyi.system.dto.menu.req.MenuQryReqDTO;
import com.ruoyi.system.dto.menu.req.MenuUpdDTO;
import com.ruoyi.system.service.MenuService;
import com.ruoyi.system.vo.menu.MenuTreeByRoleVo;
import com.ruoyi.system.vo.menu.MenuTreeVo;
import com.ruoyi.system.vo.menu.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-04-23 1:28
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SysMenuDomainService menuDomainService;
    @Override
    public List<MenuVo> qryMenu(MenuQryReqDTO reqDTO) {
        return menuDomainService.qryMenu(reqDTO);
    }

    @Override
    public MenuVo getMenuInfo(Long menuId) {
        return menuDomainService.getMenuInfo(menuId);
    }

    @Override
    public List<MenuTreeVo> qryTreeMenu(MenuQryReqDTO reqDTO) {
        return menuDomainService.qryTreeMenu(reqDTO);
    }

    @Override
    public MenuTreeByRoleVo menuTreeByRole(Long roleId) {
        return menuDomainService.menuTreeByRole(roleId);
    }

    @Override
    public Boolean addMenu(MenuAddDTO menuAddDTO) {
        return menuDomainService.addMenu(menuAddDTO);
    }

    @Override
    public Boolean updMenu(MenuUpdDTO menuUpdDTO) {
        return menuDomainService.updMenu(menuUpdDTO);
    }

    @Override
    public Boolean removeMenu(Long menuId) {
        return menuDomainService.removeMenu(menuId);
    }

}
