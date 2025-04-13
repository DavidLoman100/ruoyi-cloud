package com.ruoyi.system.domain.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.user.model.repository.UserRepository;
import com.ruoyi.system.dto.user.UserQryReqDTO;
import com.ruoyi.system.infrastructure.user.repository.po.SysUserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DavidLoman
 * @create 2025-03-20 23:48
 */
@Service
public class SysUserDomainService {

    @Autowired
    private UserRepository userRepository;


    public Page<SysUserPo> pageQrySysUser(UserQryReqDTO request) {
        Page<SysUserPo> page = userRepository.pageQrySysUser(request);
        return page;
    }
}
