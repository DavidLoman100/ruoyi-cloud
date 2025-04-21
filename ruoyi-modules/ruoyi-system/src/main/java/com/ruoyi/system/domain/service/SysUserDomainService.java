package com.ruoyi.system.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListDto;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.domain.user.entity.UserEntity;
import com.ruoyi.system.domain.user.entity.UserQryEntity;
import com.ruoyi.system.domain.user.repository.UserRepository;
import com.ruoyi.system.dto.user.req.UserQryReqDTO;
import com.ruoyi.system.dto.user.req.UserUpdReqDTO;
import com.ruoyi.system.dto.user.res.UserResDTO;
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


    public PageListDto<UserResDTO> pageQrySysUser(UserQryReqDTO request) {
        UserQryEntity userQryEntity = UserAssembler.INSTANCE.toUserEntity(request);
        Page<SysUserPo> page = userRepository.pageQrySysUser(userQryEntity);
        PageListDto<UserResDTO> pageListDto = UserAssembler.INSTANCE.toPageListDto(page);
        return pageListDto;
    }

    public Boolean updUserInfo(UserUpdReqDTO userUpdReqDTO) {
        //TODO
        //校验是否admin
        //校验用户权限
        //校验部门权限
        //校验角色权限
        //校验用户名/手机号/邮箱是否重复
        SysUserPo sysUserPo = UserAssembler.INSTANCE.toSysUserPO(userUpdReqDTO);
        return userRepository.updUserInfo(sysUserPo);
    }
}
