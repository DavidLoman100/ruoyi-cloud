package com.ruoyi.system.domain.role.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.role.entity.RolePageQryEntity;
import com.ruoyi.system.infrastructure.role.repository.po.SysRolePo;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-04-26 18:06
 */
public interface SysRoleRepository {
    SysRolePo getRoleInfo(Long roleId);

    List<SysRolePo> getRoleInfo(Collection<? extends Serializable> roleId);

    Page<SysRolePo> pageQryRoleList(RolePageQryEntity entity);

    Page<SysRolePo> pageQryRoleListByPerms(RolePageQryEntity rolePageQryEntity);
}
