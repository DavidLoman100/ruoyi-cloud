package com.ruoyi.system.infrastructure.role.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.system.domain.role.entity.RolePageQryEntity;
import com.ruoyi.system.domain.role.repository.SysRoleRepository;
import com.ruoyi.system.infrastructure.role.repository.mapper.SysRoleMapper;
import com.ruoyi.system.infrastructure.role.repository.po.SysRolePo;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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

    @Override
    public List<SysRolePo> getRoleInfo(Collection<? extends Serializable> roleId) {
        return sysRoleMapper.selectBatchIds(roleId);
    }

    @Override
    public Page<SysRolePo> pageQryRoleList(RolePageQryEntity entity) {
        Page<SysRolePo> rolePoPage = new Page<>(entity.getPageNum(), entity.getPageSize());
        Page<SysRolePo> pageResult = sysRoleMapper.selectPage(rolePoPage, Wrappers.<SysRolePo>lambdaQuery()
                .like(StringUtils.hasText(entity.getRoleName()), SysRolePo::getRoleName, entity.getRoleName())
                .like(StringUtils.hasText(entity.getRoleKey()), SysRolePo::getRoleKey, entity.getRoleKey())
                .eq(Objects.nonNull(entity.getStatus()), SysRolePo::getStatus, entity.getStatus())
                .ge(Objects.nonNull(entity.getStartCreateTime()), SysRolePo::getCreateTime, entity.getStartCreateTime())
                .le(Objects.nonNull(entity.getEndCreateTime()), SysRolePo::getCreateTime, entity.getEndCreateTime()));
        return pageResult;
    }

    @Override
    public List<SysRolePo> qryRoleList(RolePageQryEntity entity) {
        List<SysRolePo> sysRolePos = sysRoleMapper.selectList(Wrappers.<SysRolePo>lambdaQuery()
                .ne(Objects.nonNull(entity.getRoleId()), SysRolePo::getRoleId, entity.getRoleId())
                .and(i -> i.eq(StringUtils.hasText(entity.getRoleKey()), SysRolePo::getRoleKey, entity.getRoleKey())
                        .or().eq(StringUtils.hasText(entity.getRoleName()), SysRolePo::getRoleName, entity.getRoleName()))
                .eq(Objects.nonNull(entity.getStatus()), SysRolePo::getStatus, entity.getStatus())
                .ge(Objects.nonNull(entity.getStartCreateTime()), SysRolePo::getCreateTime, entity.getStartCreateTime())
                .le(Objects.nonNull(entity.getEndCreateTime()), SysRolePo::getCreateTime, entity.getEndCreateTime()));
        return sysRolePos;
    }

    @Override
    public Page<SysRolePo> pageQryRoleListByPerms(RolePageQryEntity entity) {
        Page<SysRolePo> rolePoPage = new Page<>(entity.getPageNum(), entity.getPageSize());
        return sysRoleMapper.pageQryRoleListByPerms(rolePoPage, entity);
    }

    @Override
    public SysRolePo getRoleByPerms(Long roleId, String dataScopeSql) {
        return sysRoleMapper.getRoleByPerms(roleId, dataScopeSql);
    }

    @Override
    public Boolean addRole(SysRolePo sysRolePo) {
        return sysRoleMapper.insert(sysRolePo) > 0 ? true : false;
    }

    @Override
    public Boolean updRole(SysRolePo sysRolePo) {
        return sysRoleMapper.updateById(sysRolePo) > 0 ? true : false;
    }

}
