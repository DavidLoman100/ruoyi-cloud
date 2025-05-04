package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.common.core.commonEntity.Response;
import com.ruoyi.system.dto.menu.req.MenuQryReqDTO;
import com.ruoyi.system.service.MenuService;
import com.ruoyi.system.vo.menu.MenuTreeByRoleVo;
import com.ruoyi.system.vo.menu.MenuTreeVo;
import com.ruoyi.system.vo.menu.MenuVo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.service2.ISysMenuService;

/**
 * 菜单信息
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController
{
    @Autowired
    private ISysMenuService SysMenuService;

    @Autowired
    private MenuService menuService;

    @Operation(summary = "获取菜单列表")
    @RequiresPermissions("system:menu:list")
    @PostMapping("/qry/list")
    public Response<List<MenuVo>> qryMenu(@RequestBody MenuQryReqDTO reqDTO) {
        List<MenuVo> menuVos = menuService.qryMenu(reqDTO);
        return Response.ok(menuVos);
    }

    @Operation(summary = "获取菜单详情")
    @RequiresPermissions("system:menu:query")
    @GetMapping(value = "/{menuId}")
    public Response<MenuVo> getMenuInfo(@PathVariable Long menuId) {
        return Response.ok(menuService.getMenuInfo(menuId));
    }

    @Operation(summary = "获取菜单下拉树列表")
    @PostMapping("/tree/list")
    public Response<List<MenuTreeVo>> qryTreeMenu(@RequestBody MenuQryReqDTO reqDTO){
        List<MenuTreeVo> menuTreeVos = menuService.qryTreeMenu(reqDTO);
        return Response.ok(menuTreeVos);
    }

    @Operation(summary = "获取菜单树和角色对应菜单id集")
    @GetMapping(value = "/tree/{roleId}")
    public Response<MenuTreeByRoleVo> menuTreeByRole(@PathVariable("roleId") Long roleId)
    {
        return Response.ok(menuService.menuTreeByRole(roleId));
    }

    /**
     * 新增菜单
     */
    @RequiresPermissions("system:menu:add")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysMenu menu)
    {
        if (!SysMenuService.checkMenuNameUnique(menu))
        {
            return error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath()))
        {
            return error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        menu.setCreateBy(SecurityUtils.getUsername());
        return toAjax(SysMenuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @RequiresPermissions("system:menu:edit")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysMenu menu)
    {
        if (!SysMenuService.checkMenuNameUnique(menu))
        {
            return error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath()))
        {
            return error("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        else if (menu.getMenuId().equals(menu.getParentId()))
        {
            return error("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menu.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(SysMenuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @RequiresPermissions("system:menu:remove")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{menuId}")
    public AjaxResult remove(@PathVariable("menuId") Long menuId)
    {
        if (SysMenuService.hasChildByMenuId(menuId))
        {
            return warn("存在子菜单,不允许删除");
        }
        if (SysMenuService.checkMenuExistRole(menuId))
        {
            return warn("菜单已分配,不允许删除");
        }
        return toAjax(SysMenuService.deleteMenuById(menuId));
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
         List<SysMenu> menus = SysMenuService.selectMenuTreeByUserId(userId);
        return success(SysMenuService.buildMenus(menus));
    }
}