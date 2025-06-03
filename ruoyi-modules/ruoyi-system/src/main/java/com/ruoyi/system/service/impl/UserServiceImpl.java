package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.domain.service.SysRoleDomainService;
import com.ruoyi.system.domain.service.SysUserDomainService;
import com.ruoyi.system.domain.user.entity.UserRoleQryEntity;
import com.ruoyi.system.dto.user.req.UserQryReqDTO;
import com.ruoyi.system.dto.user.req.UserRolePageQryDTO;
import com.ruoyi.system.dto.user.req.UserUpdReqDTO;
import com.ruoyi.system.infrastructure.user.repository.po.SysUserPo;
import com.ruoyi.system.service.UserService;
import com.ruoyi.system.service.assembler.UserAssembler;
import com.ruoyi.system.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 37504
 * @description 针对表【sys_user(用户信息表)】的数据库操作Service实现
 * @createDate 2025-03-21 00:14:08
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserDomainService sysUserDomainService;

    @Autowired
    private SysRoleDomainService sysRoleDomainService;

    @Override
    public PageListVo<UserVo> pageQrySysUser(UserQryReqDTO request) {
        return sysUserDomainService.pageQrySysUser(request);
    }

    @Override
    public Boolean updUserInfo(UserUpdReqDTO userUpdReqDTO) {
        return sysUserDomainService.updUserInfo(userUpdReqDTO);
    }

    @Override
    public PageListVo<UserVo> pageQryUserRole(UserRolePageQryDTO qryDTO) {
        String permsSql = sysRoleDomainService.getPermsSql("d", "u");
        UserRoleQryEntity qryEntity = UserAssembler.INSTANCE.toUserRoleQryEntity(qryDTO);
        qryEntity.setDataScopeSql(permsSql);
        Page<SysUserPo> sysUserPoPage = sysUserDomainService.pageQryUserRoleByPerms(qryEntity);
        return UserAssembler.INSTANCE.toPageListVo(sysUserPoPage);
    }

}




