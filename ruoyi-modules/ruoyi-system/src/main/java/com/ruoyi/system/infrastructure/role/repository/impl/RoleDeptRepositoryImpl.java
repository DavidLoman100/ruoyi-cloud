package com.ruoyi.system.infrastructure.role.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.domain.role.repository.RoleDeptRepository;
import com.ruoyi.system.infrastructure.role.repository.mapper.SysRoleDeptMapper;
import com.ruoyi.system.infrastructure.role.repository.po.SysRoleDeptPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author DavidLoman
 * @create 2025-04-26 18:01
 */
@Repository
public class RoleDeptRepositoryImpl implements RoleDeptRepository {

    @Autowired
    private SysRoleDeptMapper roleDeptMapper;

    @Override
    public List<SysRoleDeptPo> qryRoleDept(Set<String> roles) {
        return roleDeptMapper.selectList(Wrappers.<SysRoleDeptPo>lambdaQuery()
                .in(SysRoleDeptPo::getRoleId, roles));
    }
}
