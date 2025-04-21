package com.ruoyi.system.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListDto;
import com.ruoyi.common.core.enums.CommonEnum;
import com.ruoyi.common.core.enums.UserEnum;
import com.ruoyi.common.core.exception.BizException;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.domain.user.entity.UserEntity;
import com.ruoyi.system.domain.user.entity.UserQryEntity;
import com.ruoyi.system.domain.user.repository.UserRepository;
import com.ruoyi.system.dto.user.req.UserQryReqDTO;
import com.ruoyi.system.dto.user.req.UserUpdReqDTO;
import com.ruoyi.system.dto.user.res.UserResDTO;
import com.ruoyi.system.infrastructure.user.repository.po.SysUserPo;
import com.ruoyi.system.service.assembler.UserAssembler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        if (1L == userUpdReqDTO.getUserId()) {
            throw new BizException(UserEnum.NO_OPT_ADMIN);
        }
        //校验用户权限
        SysUserPo sysUserPoDb = userRepository.getUserWithDc(userUpdReqDTO.getUserId());
        if (Objects.isNull(sysUserPoDb)) {
            throw new BizException(CommonEnum.NO_PERMISSION);
        }
        //校验部门权限
        //校验角色权限
        //校验用户名/手机号/邮箱是否重复
        SysUserPo sysUserPo = UserAssembler.INSTANCE.toSysUserPO(userUpdReqDTO);
        return userRepository.updUserInfo(sysUserPo);
    }
}
