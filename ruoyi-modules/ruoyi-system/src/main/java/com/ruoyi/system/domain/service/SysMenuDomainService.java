package com.ruoyi.system.domain.service;

import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.constant.SystemConstants;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.enums.dict.IsTrueEnum;
import com.ruoyi.common.core.enums.error.CommonErrorEnum;
import com.ruoyi.common.core.enums.error.MenuErrorEnum;
import com.ruoyi.common.core.exception.BizException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.SysRole;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.menu.entity.MenuQryEntity;
import com.ruoyi.system.domain.menu.repository.SysMenuRepository;
import com.ruoyi.system.domain.role.repository.SysRoleMenuRepository;
import com.ruoyi.system.domain.role.repository.SysRoleRepository;
import com.ruoyi.system.domain.vo.MetaVo;
import com.ruoyi.system.domain.vo.RouterVo;
import com.ruoyi.system.dto.menu.req.MenuAddDTO;
import com.ruoyi.system.dto.menu.req.MenuQryReqDTO;
import com.ruoyi.system.dto.menu.req.MenuUpdDTO;
import com.ruoyi.system.infrastructure.menu.repository.po.SysMenuPo;
import com.ruoyi.system.infrastructure.role.repository.po.SysRolePo;
import com.ruoyi.system.service.assembler.MenuAssembler;
import com.ruoyi.system.vo.menu.MenuTreeByRoleVo;
import com.ruoyi.system.vo.menu.MenuTreeVo;
import com.ruoyi.system.vo.menu.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author DavidLoman
 * @create 2025-04-23 0:09
 */
@Service
public class SysMenuDomainService {

    @Autowired
    private SysMenuRepository sysMenuRepository;

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Autowired
    private SysRoleMenuRepository sysRoleMenuRepository;

    /**
     * 查询菜单列表
     * @param reqDTO
     * @return
     */
    public List<MenuVo> qryMenu(MenuQryReqDTO reqDTO) {
        MenuQryEntity qryEntity = MenuAssembler.INSTANCE.toMenuQryEntity(reqDTO);
        List<SysMenuPo> sysMenuPos = getMenuList(qryEntity);
        if (!CollectionUtils.isEmpty(sysMenuPos)) {
            return MenuAssembler.INSTANCE.toMenuVoList(sysMenuPos);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 获取菜单列表
     * 介绍 ：菜单列表
     * 1.如果是超级管理员，则获取全部菜单
      * 2.如果是普通用户，则获取用户关联的角色的菜单
     * @param qryEntity
     * @return
     */
    private List<SysMenuPo> getMenuList(MenuQryEntity qryEntity) {
        boolean isAdmin = SystemConstants.ADMIN_USER.equals(SecurityUtils.getUsername());
        List<SysMenuPo> sysMenuPos;
        if (isAdmin) {
            sysMenuPos = sysMenuRepository.qryMenu(qryEntity);
        } else {
            List<SysRole> sysRoles = SecurityUtils.getLoginUser().getSysUser().getRoles();
            List<Long> roleIds = sysRoles.stream().map(SysRole::getRoleId).collect(Collectors.toList());
            qryEntity.setRoles(roleIds);
            sysMenuPos = sysMenuRepository.qryMenuByPermission(qryEntity);
        }
        return sysMenuPos;
    }

    public MenuVo getMenuInfo(Long menuId) {
        SysMenuPo sysMenuPo = sysMenuRepository.getMenuById(menuId);
        if (Objects.isNull(sysMenuPo)) {
            throw new BizException(CommonErrorEnum.INVALID_INFO);
        }
        MenuVo menuVo = MenuAssembler.INSTANCE.toMenuVo(sysMenuPo);
        return menuVo;
    }

    /**
     * 获取菜单树
     * @param reqDTO
     * @return
     */
    public List<MenuTreeVo> qryTreeMenu(MenuQryReqDTO reqDTO) {
        MenuQryEntity qryEntity = MenuAssembler.INSTANCE.toMenuQryEntity(reqDTO);
        return qryMenuTree(qryEntity);
    }

    private List<MenuTreeVo> qryMenuTree (MenuQryEntity qryEntity) {
        List<SysMenuPo> sysMenuPos = getMenuList(qryEntity);
        if (!CollectionUtils.isEmpty(sysMenuPos)) {
            Map<Long, List<SysMenuPo>> menuMap = sysMenuPos.stream().collect(Collectors.groupingBy(SysMenuPo::getParentId));
            Long minParentId = menuMap.keySet().stream().min(Long::compareTo).get();
            List<SysMenuPo> rootMenuPos = menuMap.get(minParentId);
            List<MenuTreeVo> rootMenuTreeVos = MenuAssembler.INSTANCE.toMenuTreeVos(rootMenuPos);
            setTreeMenuChildren(rootMenuTreeVos, menuMap);
            return rootMenuTreeVos;
        }
        return Collections.EMPTY_LIST;
    }

    private void setTreeMenuChildren(List<MenuTreeVo> menuTreeVos, Map<Long, List<SysMenuPo>> menuMap) {
        for (MenuTreeVo menuTreeVo : menuTreeVos) {
            if (menuMap.containsKey(menuTreeVo.getMenuId())) {
                List<SysMenuPo> childMenuPos = menuMap.get(menuTreeVo.getMenuId());
                List<MenuTreeVo> childMenuTreeVos = MenuAssembler.INSTANCE.toMenuTreeVos(childMenuPos);
                menuTreeVo.setChildren(childMenuTreeVos);
                setTreeMenuChildren(childMenuTreeVos, menuMap);
            }
        }
    }

    /**
     * 获取菜单树 和 角色关联的菜单ID
     * @param roleId
     * @return
     */
    public MenuTreeByRoleVo menuTreeByRole(Long roleId) {
        SysRolePo sysRolePo = sysRoleRepository.getRoleInfo(roleId);
        if (Objects.isNull(sysRolePo)) {
            throw new BizException(CommonErrorEnum.INVALID_INFO);
        }
        List<MenuTreeVo> menuTreeVos = qryMenuTree(new MenuQryEntity());
        List<Long> menuIds = sysMenuRepository.getMenuIdByRole(roleId,sysRolePo.getMenuCheckStrictly());
        return new MenuTreeByRoleVo(menuIds, menuTreeVos);
    }

    public Boolean addMenu(MenuAddDTO menuAddDTO) {
        SysMenuPo sysMenuPo = MenuAssembler.INSTANCE.toSysMenuPo(menuAddDTO);
        checkMenuName(sysMenuPo);
        checkPatentId(sysMenuPo);
        return sysMenuRepository.addMenu(sysMenuPo);
    }

    private void checkMenuName(SysMenuPo sysMenuPo) {
        Boolean nameExist = sysMenuRepository.checkNameExist(sysMenuPo.getMenuId(), sysMenuPo.getMenuName());
        if (nameExist) {
            throw new BizException(MenuErrorEnum.NAME_EXIST);
        }
        if (Integer.valueOf(IsTrueEnum.TRUE.getCode()) == sysMenuPo.getIsFrame()
                && StringUtils.isNotBlank(sysMenuPo.getPath())
                && !StringUtils.startsWithAny(sysMenuPo.getPath(), Constants.HTTP, Constants.HTTPS)) {
            throw new BizException(MenuErrorEnum.NO_VALID_PATH);
        }
    }

    private void checkPatentId(SysMenuPo sysMenuPo) {
        if (sysMenuPo.getParentId() == 0L) {
            return;
        }
        SysMenuPo pSysMenuPo = sysMenuRepository.getMenuById(sysMenuPo.getParentId());
        if (Objects.isNull(pSysMenuPo)) {
            throw new BizException(MenuErrorEnum.NO_VALID_PARENT);
        }
    }

    public Boolean updMenu(MenuUpdDTO menuUpdDTO) {
        SysMenuPo sysMenuPo = MenuAssembler.INSTANCE.toSysMenuPo(menuUpdDTO);
        SysMenuPo menuPoDb = sysMenuRepository.getMenuById(sysMenuPo.getMenuId());
        if (Objects.isNull(menuPoDb)) {
            throw new BizException(CommonErrorEnum.INVALID_INFO);
        }
        checkMenuName(sysMenuPo);
        checkPatentId(sysMenuPo);
        return sysMenuRepository.updMenu(sysMenuPo);
    }

    public Boolean removeMenu(Long menuId) {
        SysMenuPo menuPo = sysMenuRepository.getMenuById(menuId);
        if (Objects.isNull(menuPo)) {
            throw new BizException(CommonErrorEnum.INVALID_INFO);
        }
        if (sysMenuRepository.hasChild(menuId)) {
            throw new BizException(MenuErrorEnum.HAS_CHILD);
        }
        if (sysRoleMenuRepository.hasRoleMenu(menuId)) {
            throw new BizException(MenuErrorEnum.HAS_ASSIGNED);
        }
        return sysMenuRepository.deleteMenu(menuId);
    }

    public List<RouterVo> getRouters() {
        MenuQryEntity qryEntity = new MenuQryEntity();
        qryEntity.setStatus(SystemConstants.ZERO_STR);
        List<MenuTreeVo> menuTreeVos = qryMenuTree(qryEntity);
        if (CollectionUtils.isEmpty(menuTreeVos)) {
            return new ArrayList<RouterVo>();
        }

        return buildMenus(menuTreeVos);
    }

    private List<RouterVo> buildMenus(List<MenuTreeVo> menuTreeVos)
    {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (MenuTreeVo menuTreeVo : menuTreeVos)
        {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menuTreeVo.getVisible()));
            router.setName(getRouteName(menuTreeVo));
            router.setPath(getRouterPath(menuTreeVo));
            router.setComponent(getComponent(menuTreeVo));
            router.setQuery(menuTreeVo.getQuery());
            router.setMeta(new MetaVo(menuTreeVo.getMenuName(), menuTreeVo.getIcon(), SystemConstants.ONE==menuTreeVo.getIsCache(), menuTreeVo.getPath()));
            List<MenuTreeVo> cMenus = menuTreeVo.getChildren();
            if (StringUtils.isNotEmpty(cMenus) && UserConstants.TYPE_DIR.equals(menuTreeVo.getMenuType()))
            {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            }
            else if (isMenuFrame(menuTreeVo))
            {
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menuTreeVo.getPath());
                children.setComponent(menuTreeVo.getComponent());
                children.setName(getRouteName(menuTreeVo.getRouteName(), menuTreeVo.getPath()));
                children.setMeta(new MetaVo(menuTreeVo.getMenuName(), menuTreeVo.getIcon(), SystemConstants.ONE == menuTreeVo.getIsCache(), menuTreeVo.getPath()));
                children.setQuery(menuTreeVo.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            else if (menuTreeVo.getParentId().intValue() == 0 && isInnerLink(menuTreeVo))
            {
                router.setMeta(new MetaVo(menuTreeVo.getMenuName(), menuTreeVo.getIcon()));
                router.setPath("/");
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                String routerPath = innerLinkReplaceEach(menuTreeVo.getPath());
                children.setPath(routerPath);
                children.setComponent(UserConstants.INNER_LINK);
                children.setName(getRouteName(menuTreeVo.getRouteName(), routerPath));
                children.setMeta(new MetaVo(menuTreeVo.getMenuName(), menuTreeVo.getIcon(), menuTreeVo.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由名称
     *
     * @param menuTreeVo 菜单信息
     * @return 路由名称
     */
    public String getRouteName(MenuTreeVo menuTreeVo )
    {
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menuTreeVo))
        {
            return StringUtils.EMPTY;
        }
        return getRouteName(menuTreeVo.getRouteName(), menuTreeVo.getPath());
    }

    /**
     * 获取路由名称，如没有配置路由名称则取路由地址
     *
     * @param name 路由名称
     * @param path 路由地址
     * @return 路由名称（驼峰格式）
     */
    public String getRouteName(String name, String path)
    {
        String routerName = StringUtils.isNotEmpty(name) ? name : path;
        return StringUtils.capitalize(routerName);
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menuTreeVo 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(MenuTreeVo menuTreeVo)
    {
        return menuTreeVo.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menuTreeVo.getMenuType())
                && menuTreeVo.getIsFrame().equals(UserConstants.NO_FRAME);
    }

    /**
     * 获取路由地址
     *
     * @param menuTreeVo 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(MenuTreeVo menuTreeVo )
    {
        String routerPath = menuTreeVo.getPath();
        // 内链打开外网方式
        if (menuTreeVo.getParentId().intValue() != 0 && isInnerLink(menuTreeVo))
        {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (0 == menuTreeVo.getParentId().intValue() && UserConstants.TYPE_DIR.equals(menuTreeVo.getMenuType())
                && UserConstants.NO_FRAME.equals(menuTreeVo.getIsFrame().toString()))
        {
            routerPath = "/" + menuTreeVo.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menuTreeVo))
        {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menuTreeVo 菜单信息
     * @return 组件信息
     */
    public String getComponent(MenuTreeVo menuTreeVo )
    {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menuTreeVo.getComponent()) && !isMenuFrame(menuTreeVo))
        {
            component = menuTreeVo.getComponent();
        }
        else if (StringUtils.isEmpty(menuTreeVo.getComponent()) && menuTreeVo.getParentId().intValue() != 0 && isInnerLink(menuTreeVo))
        {
            component = UserConstants.INNER_LINK;
        }
        else if (StringUtils.isEmpty(menuTreeVo.getComponent()) && isParentView(menuTreeVo))
        {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为parent_view组件
     *
     * @param menuTreeVo 菜单信息
     * @return 结果
     */
    public boolean isParentView(MenuTreeVo menuTreeVo )
    {
        return menuTreeVo.getParentId().intValue() != 0 && UserConstants.TYPE_DIR.equals(menuTreeVo.getMenuType());
    }

    /**
     * 是否为内链组件
     *
     * @param menuTreeVo 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(MenuTreeVo menuTreeVo )
    {
        return menuTreeVo.getIsFrame().equals(UserConstants.NO_FRAME) && StringUtils.ishttp(menuTreeVo.getPath());
    }

    /**
     * 内链域名特殊字符替换
     *
     * @return 替换后的内链域名
     */
    public String innerLinkReplaceEach(String path)
    {
        return StringUtils.replaceEach(path, new String[] { Constants.HTTP, Constants.HTTPS, Constants.WWW, ".", ":" },
                new String[] { "", "", "", "/", "/" });
    }

}