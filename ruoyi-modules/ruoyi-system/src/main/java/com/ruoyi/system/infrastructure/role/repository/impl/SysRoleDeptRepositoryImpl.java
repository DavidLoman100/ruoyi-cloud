package com.ruoyi.system.infrastructure.role.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.domain.role.repository.SysRoleDeptRepository;
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
public class SysRoleDeptRepositoryImpl implements SysRoleDeptRepository {

    @Autowired
    private SysRoleDeptMapper roleDeptMapper;

    @Override
    public List<SysRoleDeptPo> qryRoleDept(Set<String> roles) {
        return roleDeptMapper.selectList(Wrappers.<SysRoleDeptPo>lambdaQuery()
                .in(SysRoleDeptPo::getRoleId, roles));
    }

    @Override
    public Boolean hasRoleDept(Long roleId) {
        return roleDeptMapper.selectCount(Wrappers.<SysRoleDeptPo>lambdaQuery()
                .eq(SysRoleDeptPo::getRoleId, roleId)) > 0 ? true : false;
    }

    @Override
    public Boolean deleteRoleDept(Long roleId) {
        return roleDeptMapper.delete(Wrappers.<SysRoleDeptPo>lambdaQuery()
                .eq(SysRoleDeptPo::getRoleId, roleId)) > 0 ? true : false;
    }

    @Override
    public Boolean deleteBatchRoleDept(List<Long> roleIds) {
        return roleDeptMapper.delete(Wrappers.<SysRoleDeptPo>lambdaQuery()
                .in(SysRoleDeptPo::getRoleId, roleIds)) > 0 ? true : false;
    }

    @Override
    public Boolean addRoleDept(List<SysRoleDeptPo> sysRoleDeptPos) {
        return roleDeptMapper.insertBatch(sysRoleDeptPos) > 0 ? true : false;
    }

}
