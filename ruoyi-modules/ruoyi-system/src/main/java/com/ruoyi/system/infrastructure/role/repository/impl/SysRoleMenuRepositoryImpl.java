package com.ruoyi.system.infrastructure.role.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.domain.role.repository.SysRoleMenuRepository;
import com.ruoyi.system.infrastructure.role.repository.mapper.SysRoleMenuMapper;
import com.ruoyi.system.infrastructure.role.repository.po.SysRoleMenuPo;
import com.ruoyi.system.mapper.SysRoleMenuMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author DavidLoman
 * @create 2025-04-26 18:08
 */
@Repository
public class SysRoleMenuRepositoryImpl implements SysRoleMenuRepository {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Override
    public boolean hasRoleMenu(Long menuId) {
        Long count = sysRoleMenuMapper.selectCount(Wrappers.<SysRoleMenuPo>lambdaQuery()
                .eq(SysRoleMenuPo::getMenuId, menuId));
        return count > 0 ? true : false;
    }
}
