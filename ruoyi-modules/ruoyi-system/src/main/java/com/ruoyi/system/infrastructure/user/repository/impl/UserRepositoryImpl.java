package com.ruoyi.system.infrastructure.user.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.datascope.annotation.DataScope;
import com.ruoyi.system.domain.user.entity.UserEntity;
import com.ruoyi.system.domain.user.entity.UserQryEntity;
import com.ruoyi.system.domain.user.repository.UserRepository;
import com.ruoyi.system.infrastructure.user.repository.po.SysUserPo;
import com.ruoyi.system.infrastructure.user.repository.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author DavidLoman
 * @create 2025-04-14 1:14
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public Page<SysUserPo> pageQrySysUser(UserQryEntity entity) {
        Page<SysUserPo> page = new Page<>(entity.getPageNum(), entity.getPageSize());
        LambdaQueryWrapper<SysUserPo> wrapper = Wrappers.<SysUserPo>lambdaQuery()
                .eq(!Objects.isNull(entity.getUserId()), SysUserPo::getUserId, entity.getUserId())
                .eq(!Objects.isNull(entity.getDeptId()), SysUserPo::getDeptId, entity.getDeptId())
                .like(StringUtils.hasText(entity.getUserName()), SysUserPo::getUserName, entity.getUserName())
                .eq(StringUtils.hasText(entity.getUserType()), SysUserPo::getUserType, entity.getUserType())
                .like(StringUtils.hasText(entity.getEmail()), SysUserPo::getEmail, entity.getEmail())
                .like(StringUtils.hasText(entity.getPhonenumber()), SysUserPo::getPhonenumber, entity.getPhonenumber())
                .eq(StringUtils.hasText(entity.getSex()), SysUserPo::getSex, entity.getSex())
                .eq(StringUtils.hasText(entity.getStatus()), SysUserPo::getStatus, entity.getStatus());
        return sysUserMapper.selectPage(page, wrapper);
    }

    @Override
    public Boolean updUserInfo(SysUserPo sysUserPo) {
        int upd = sysUserMapper.updateById(sysUserPo);
        return upd > 0 ? true : false;
    }

    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public SysUserPo getUserWithDc(Long userId) {
        return null;
    }
}
