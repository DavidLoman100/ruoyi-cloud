package com.ruoyi.system.domain.service;

import com.ruoyi.common.core.constant.SystemConstants;
import com.ruoyi.common.core.enums.CommonEnum;
import com.ruoyi.common.core.exception.BizException;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.SysRole;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.domain.menu.entity.MenuQryEntity;
import com.ruoyi.system.domain.menu.repository.SysMenuRepository;
import com.ruoyi.system.domain.user.entity.UserQryEntity;
import com.ruoyi.system.dto.menu.req.MenuQryReqDTO;
import com.ruoyi.system.infrastructure.menu.repository.po.SysMenuPo;
import com.ruoyi.system.service.assembler.MenuAssembler;
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

    public List<MenuVo> qryMenu(MenuQryReqDTO reqDTO) {
        MenuQryEntity qryEntity = MenuAssembler.INSTANCE.toMenuQryEntity(reqDTO);
        List<SysMenuPo> sysMenuPos = getList(qryEntity);
        if (!CollectionUtils.isEmpty(sysMenuPos)) {
            return MenuAssembler.INSTANCE.toMenuVoList(sysMenuPos);
        }
        return Collections.EMPTY_LIST;
    }

    private List<SysMenuPo> getList(MenuQryEntity qryEntity) {
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
            throw new BizException(CommonEnum.INVALID_INFO);
        }
        MenuVo menuVo = MenuAssembler.INSTANCE.toMenuVo(sysMenuPo);
        return menuVo;
    }

    public List<MenuTreeVo> qryTreeMenu(MenuQryReqDTO reqDTO) {
        MenuQryEntity qryEntity = MenuAssembler.INSTANCE.toMenuQryEntity(reqDTO);
        List<SysMenuPo> sysMenuPos = getList(qryEntity);
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
}