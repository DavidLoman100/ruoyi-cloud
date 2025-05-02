package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.service.SysMenuDomainService;
import com.ruoyi.system.dto.menu.req.MenuQryReqDTO;
import com.ruoyi.system.service.MenuService;
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
}
