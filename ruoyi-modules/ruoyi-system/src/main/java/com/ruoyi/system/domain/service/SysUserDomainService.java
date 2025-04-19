package com.ruoyi.system.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.user.entity.UserQryEntity;
import com.ruoyi.system.domain.user.repository.UserRepository;
import com.ruoyi.system.dto.user.req.UserQryReqDTO;
import com.ruoyi.system.infrastructure.user.repository.po.SysUserPo;
import com.ruoyi.system.service.assembler.UserAssembler;
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
        UserQryEntity userQryEntity = UserAssembler.INSTANCE.toUserEntity(request);
        Page<SysUserPo> page = userRepository.pageQrySysUser(userQryEntity);
        return page;
    }

    public void updUserInfo() {


    }
}
