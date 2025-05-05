package com.ruoyi.system.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.common.core.constant.SystemConstants;
import com.ruoyi.common.core.enums.error.UserErrorEnum;
import com.ruoyi.common.core.exception.BizException;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.domain.role.repository.RoleDeptRepository;
import com.ruoyi.system.domain.user.entity.UserQryEntity;
import com.ruoyi.system.domain.user.repository.SysUserRepository;
import com.ruoyi.system.dto.user.req.UserQryReqDTO;
import com.ruoyi.system.dto.user.req.UserUpdReqDTO;
import com.ruoyi.system.infrastructure.user.repository.po.SysUserPo;
import com.ruoyi.system.service.assembler.UserAssembler;
import com.ruoyi.system.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author DavidLoman
 * @create 2025-03-20 23:48
 */
@Service
public class SysUserDomainService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private RoleDeptRepository roleDeptRepository;


    public PageListVo<UserVo> pageQrySysUser(UserQryReqDTO request) {
        UserQryEntity userQryEntity = UserAssembler.INSTANCE.toUserEntity(request);
        //不是超管 先查角色部门表，获取部门ID 再查询用户表
        //超管管可以查询所有
        boolean isAdmin = SystemConstants.ADMIN_USER.equals(SecurityUtils.getUsername());
        if (!isAdmin) {
            LoginUser curUser = SecurityUtils.getLoginUser();
            Set<String> roles = curUser.getRoles();
            if (CollectionUtils.isEmpty(roles)) {
                throw new BizException(UserErrorEnum.ACCOUNT_ERROR);
            }
            userQryEntity.setDeptId(curUser.getSysUser().getDeptId());
        }

        Page<SysUserPo> page = sysUserRepository.pageQrySysUser(userQryEntity);
        PageListVo<UserVo> pageListVo = UserAssembler.INSTANCE.toPageListVo(page);
        return pageListVo;
    }

    public Boolean updUserInfo(UserUpdReqDTO userUpdReqDTO) {
        //TODO

        //不允许修改超级管理员
        if (1L == userUpdReqDTO.getUserId()) {
            throw new BizException(UserErrorEnum.NO_OPT_ADMIN);
        }


        checkPermission(userUpdReqDTO.getUserId());

        //校验部门权限
        //校验角色权限
        //校验用户名/手机号/邮箱是否重复
        SysUserPo sysUserPo = UserAssembler.INSTANCE.toSysUserPO(userUpdReqDTO);
        return sysUserRepository.updUserInfo(sysUserPo);
    }

    private Boolean checkPermission(Long userId) {

//        if (SystemConstants.SYSTEM_USER.equals(SecurityUtils.getUsername())) {
//            return true;
//        }

        //校验用户权限
        //SysUserPo sysUserPoDb = sysUserRepository.getUserWithDc(userId);
//        if (Objects.isNull(sysUserPoDb) ) {
//            throw new BizException(CommonErrorEnum.NO_PERMISSION);
//        }
        return true;
    }
}
