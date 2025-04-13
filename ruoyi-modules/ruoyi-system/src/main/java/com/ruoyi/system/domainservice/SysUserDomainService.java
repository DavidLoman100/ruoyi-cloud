package com.ruoyi.system.domainservice;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.dao.SysUserDao;
import com.ruoyi.system.entity.SysUser;
import com.ruoyi.system.request.SysUserQryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author DavidLoman
 * @create 2025-03-20 23:48
 */
@Service
public class SysUserDomainService {

    @Autowired
    private SysUserDao sysUserDao;


    public Page<SysUser> pageQrySysUser(SysUserQryRequest request) {
        Page<SysUser> page = new Page<>(request.getPage(), request.getSize());
        LambdaQueryWrapper<SysUser> wrapper = Wrappers.<SysUser>lambdaQuery()
                .eq(!Objects.isNull(request.getUserId()), SysUser::getUserId, request.getUserId())
                .eq(!Objects.isNull(request.getDeptId()), SysUser::getDeptId, request.getDeptId())
                .like(StringUtils.hasText(request.getUserName()), SysUser::getUserName, request.getUserName())
                .eq(StringUtils.hasText(request.getUserType()), SysUser::getUserType, request.getUserType())
                .like(StringUtils.hasText(request.getEmail()), SysUser::getEmail, request.getEmail())
                .like(StringUtils.hasText(request.getPhonenumber()), SysUser::getPhonenumber, request.getPhonenumber())
                .eq(StringUtils.hasText(request.getSex()), SysUser::getSex, request.getSex())
                .eq(StringUtils.hasText(request.getStatus()), SysUser::getStatus, request.getStatus());
        return sysUserDao.selectPage(page, wrapper);
    }
}
