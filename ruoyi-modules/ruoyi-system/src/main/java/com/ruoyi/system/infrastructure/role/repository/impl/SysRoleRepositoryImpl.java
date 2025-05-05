package com.ruoyi.system.infrastructure.role.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.domain.role.repository.SysRoleRepository;
import com.ruoyi.system.infrastructure.role.repository.mapper.SysRoleMapper;
import com.ruoyi.system.infrastructure.role.repository.po.SysRolePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author DavidLoman
 * @create 2025-04-26 18:05
 */
@Repository
public class SysRoleRepositoryImpl implements SysRoleRepository {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysRolePo getRoleInfo(Long roleId) {
        return sysRoleMapper.selectById(roleId);
    }
}
