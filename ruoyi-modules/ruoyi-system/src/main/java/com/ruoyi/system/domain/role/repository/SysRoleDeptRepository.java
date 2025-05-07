package com.ruoyi.system.domain.role.repository;

import com.ruoyi.system.infrastructure.role.repository.po.SysRoleDeptPo;

import java.util.List;
import java.util.Set;

/**
 * @author DavidLoman
 * @create 2025-04-26 18:03
 */
public interface SysRoleDeptRepository {
    List<SysRoleDeptPo> qryRoleDept(Set<String> roles);
}
