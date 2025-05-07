package com.ruoyi.system.domain.service;

import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.constant.SystemConstants;
import com.ruoyi.common.core.enums.dict.IsTrueEnum;
import com.ruoyi.common.core.enums.error.CommonErrorEnum;
import com.ruoyi.common.core.enums.error.MenuErrorEnum;
import com.ruoyi.common.core.exception.BizException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.SysRole;
import com.ruoyi.system.domain.menu.entity.MenuQryEntity;
import com.ruoyi.system.domain.menu.repository.SysMenuRepository;
import com.ruoyi.system.domain.role.repository.SysRoleMenuRepository;
import com.ruoyi.system.domain.role.repository.SysRoleRepository;
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
}