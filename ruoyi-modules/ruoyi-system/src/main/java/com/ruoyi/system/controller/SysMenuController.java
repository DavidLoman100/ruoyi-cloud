package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.common.core.commonEntity.Response;
import com.ruoyi.system.domain.vo.RouterVo;
import com.ruoyi.system.dto.menu.req.MenuAddDTO;
import com.ruoyi.system.dto.menu.req.MenuQryReqDTO;
import com.ruoyi.system.dto.menu.req.MenuUpdDTO;
import com.ruoyi.system.service.MenuService;
import com.ruoyi.system.vo.menu.MenuTreeByRoleVo;
import com.ruoyi.system.vo.menu.MenuTreeVo;
import com.ruoyi.system.vo.menu.MenuVo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;

import javax.validation.Valid;

/**
 * 菜单信息
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController {


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

    @Operation(summary = "新增菜单")
    @RequiresPermissions("system:menu:add")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Response<Boolean> addMenu(@RequestBody MenuAddDTO menuAddDTO) {
        return Response.ok(menuService.addMenu(menuAddDTO));
    }

    @Operation(summary = "修改菜单")
    @RequiresPermissions("system:menu:edit")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public Response<Boolean> updMenu(@Valid @RequestBody MenuUpdDTO menuUpdDTO) {
        return Response.ok(menuService.updMenu(menuUpdDTO));
    }

    @Operation(summary = "删除菜单")
    @RequiresPermissions("system:menu:remove")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{menuId}")
    public Response<Boolean> removeMenu(@PathVariable("menuId") Long menuId) {
        return Response.ok(menuService.removeMenu(menuId));
    }

    @Operation(summary = "获取菜单路由信息")
    @GetMapping("/getRouters")
   public Response<List<RouterVo>> getRouters() {
        return Response.ok(menuService.getRouters());
    }

}