package com.ruoyi.system.infrastructure.user.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.user.repository.UserRepository;
import com.ruoyi.system.infrastructure.user.repository.po.SysUserPo;
import com.ruoyi.system.infrastructure.user.repository.mapper.SysUserMapper;
import com.ruoyi.system.dto.user.UserQryReqDTO;
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
    public Page<SysUserPo> pageQrySysUser(UserQryReqDTO request) {
        Page<SysUserPo> page = new Page<>(request.getPageNum(), request.getPageSize());
        LambdaQueryWrapper<SysUserPo> wrapper = Wrappers.<SysUserPo>lambdaQuery()
                .eq(!Objects.isNull(request.getUserId()), SysUserPo::getUserId, request.getUserId())
                .eq(!Objects.isNull(request.getDeptId()), SysUserPo::getDeptId, request.getDeptId())
                .like(StringUtils.hasText(request.getUserName()), SysUserPo::getUserName, request.getUserName())
                .eq(StringUtils.hasText(request.getUserType()), SysUserPo::getUserType, request.getUserType())
                .like(StringUtils.hasText(request.getEmail()), SysUserPo::getEmail, request.getEmail())
                .like(StringUtils.hasText(request.getPhonenumber()), SysUserPo::getPhonenumber, request.getPhonenumber())
                .eq(StringUtils.hasText(request.getSex()), SysUserPo::getSex, request.getSex())
                .eq(StringUtils.hasText(request.getStatus()), SysUserPo::getStatus, request.getStatus());
        return sysUserMapper.selectPage(page, wrapper);
    }
}
