package com.ruoyi.system.infrastructure.menu.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.domain.menu.entity.MenuQryEntity;
import com.ruoyi.system.domain.menu.repository.SysMenuRepository;
import com.ruoyi.system.infrastructure.menu.repository.mapper.SysMenuMapper;
import com.ruoyi.system.infrastructure.menu.repository.po.SysMenuPo;
import com.ruoyi.system.infrastructure.role.repository.po.SysRolePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author DavidLoman
 * @create 2025-05-01 1:05
 */
@Repository
public class SysMenuRepositoryImpl implements SysMenuRepository {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuPo> qryMenu(MenuQryEntity qryEntity) {
        return sysMenuMapper.selectList(Wrappers.<SysMenuPo>lambdaQuery()
                .eq(StringUtils.hasText(qryEntity.getStatus()), SysMenuPo::getStatus, qryEntity.getStatus())
                .like(StringUtils.hasText(qryEntity.getMenuName()), SysMenuPo::getMenuName, qryEntity.getMenuName())
                .orderByAsc(SysMenuPo::getParentId, SysMenuPo::getOrderNum));
    }

    @Override
    public List<SysMenuPo> qryMenuByPermission(MenuQryEntity qryEntity) {
        return sysMenuMapper.qryMenuByPermission(qryEntity);
    }

    @Override
    public SysMenuPo getMenuById(Long menuId) {
        return sysMenuMapper.selectById(menuId);
    }

    @Override
    public List<Long> getMenuIdByRole(Long roleId, Integer menuCheckStrictly) {
        return sysMenuMapper.getMenuIdByRole(roleId, menuCheckStrictly);
    }

    @Override
    public Boolean checkNameExist(Long menuId, String menuName) {
        Long count = sysMenuMapper.selectCount(Wrappers.<SysMenuPo>lambdaQuery()
                .eq(SysMenuPo::getMenuName, menuName)
                .ne(Objects.nonNull(menuId), SysMenuPo::getMenuId,menuId));
        return count == 0 ? false : true;
    }

    @Override
    public Boolean addMenu(SysMenuPo sysMenuPo) {
        return sysMenuMapper.insert(sysMenuPo) == 1 ? true : false;
    }

    @Override
    public Boolean updMenu(SysMenuPo sysMenuPo) {
        return sysMenuMapper.updateById(sysMenuPo) == 1 ? true : false;
    }

}
