package com.ruoyi.system.service;

import com.ruoyi.system.dto.menu.req.MenuQryReqDTO;
import com.ruoyi.system.vo.menu.MenuVo;

import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-04-23 1:29
 */
public interface SysMenuService {
    List<MenuVo> qryMenu(MenuQryReqDTO reqDTO);
}
