package com.ruoyi.system.domain.service;

import com.ruoyi.system.domain.user.repository.SysUserRoleRepository;
import com.ruoyi.system.infrastructure.user.repository.po.SysUserRolePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-05-28 0:29
 */
@Service
public class SysUserRoleDomainService {

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;


    public Boolean hasUserRole(List<Long> roleIds) {
        List<SysUserRolePo> sysUserRolePos = sysUserRoleRepository.qryUserRole(roleIds);
        if (CollectionUtils.isEmpty(sysUserRolePos)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
