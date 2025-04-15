package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.commonEntity.PageListDto;
import com.ruoyi.system.domain.service.SysUserDomainService;
import com.ruoyi.system.dto.user.UserQryReqDTO;
import com.ruoyi.system.dto.user.UserResDTO;
import com.ruoyi.system.infrastructure.user.repository.mapper.SysUserMapper;
import com.ruoyi.system.infrastructure.user.repository.po.SysUserPo;
import com.ruoyi.system.service.SysUserService;
import com.ruoyi.system.service.assembler.UserAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 37504
* @description 针对表【sys_user(用户信息表)】的数据库操作Service实现
* @createDate 2025-03-21 00:14:08
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserPo> implements SysUserService {

    @Autowired
    private SysUserDomainService sysUserDomainService;

    @Override
    public PageListDto<UserResDTO> pageQrySysUser(UserQryReqDTO request) {
        Page<SysUserPo> sysUserPage = sysUserDomainService.pageQrySysUser(request);
        PageListDto<UserResDTO> pageListDto = UserAssembler.INSTANCE.toPageListDto(sysUserPage);
        return pageListDto;
    }

    @Override
    public Boolean updUserInfo() {
        return null;
    }

}




