package com.ruoyi.system.infrastructure.user.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.domain.user.repository.SysUserRoleRepository;
import com.ruoyi.system.infrastructure.user.repository.mapper.SysUserRoleMapper;
import com.ruoyi.system.infrastructure.user.repository.po.SysUserRolePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-05-28 1:08
 */
@Repository
public class SysUserRoleRepositoryImpl implements SysUserRoleRepository {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Override
    public List<SysUserRolePo> qryUserRole(List<Long> roleIds) {
        return sysUserRoleMapper.selectList(Wrappers.<SysUserRolePo>lambdaQuery()
                .in(SysUserRolePo::getRoleId, roleIds));
    }

    @Override
    public Boolean delBatchUserRole(List<Long> roleIds) {
        return sysUserRoleMapper.delete(Wrappers.<SysUserRolePo>lambdaQuery()
                .in(SysUserRolePo::getRoleId, roleIds)) > 0;
    }

}
