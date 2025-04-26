package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.domain.service.SysUserDomainService;
import com.ruoyi.system.dto.user.req.UserQryReqDTO;
import com.ruoyi.system.dto.user.req.UserUpdReqDTO;
import com.ruoyi.system.service.SysUserService;
import com.ruoyi.system.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 37504
 * @description 针对表【sys_user(用户信息表)】的数据库操作Service实现
 * @createDate 2025-03-21 00:14:08
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDomainService sysUserDomainService;

    @Override
    public PageListVo<UserVo> pageQrySysUser(UserQryReqDTO request) {
        return sysUserDomainService.pageQrySysUser(request);
    }

    @Override
    public Boolean updUserInfo(UserUpdReqDTO userUpdReqDTO) {
        return sysUserDomainService.updUserInfo(userUpdReqDTO);
    }

}




