package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.commonEntity.PageListDto;
import com.ruoyi.system.domainservice.SysUserDomainService;
import com.ruoyi.system.dao.SysUserDao;
import com.ruoyi.system.dto.SysUserDto;
import com.ruoyi.system.entity.SysUser;
import com.ruoyi.system.mapstruct.SysUserMapper;
import com.ruoyi.system.request.SysUserQryRequest;
import com.ruoyi.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 37504
* @description 针对表【sys_user(用户信息表)】的数据库操作Service实现
* @createDate 2025-03-21 00:14:08
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    private SysUserDomainService sysUserDomainService;

    @Override
    public PageListDto<SysUserDto> pageQrySysUser(SysUserQryRequest request) {
        Page<SysUser> sysUserPage = sysUserDomainService.pageQrySysUser(request);
        PageListDto<SysUserDto> pageListDto = SysUserMapper.INSTANCE.toPageListDto(sysUserPage);
        return pageListDto;
    }

}




