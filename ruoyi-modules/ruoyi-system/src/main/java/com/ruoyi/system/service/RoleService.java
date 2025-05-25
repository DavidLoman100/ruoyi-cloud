package com.ruoyi.system.service;

import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.api.domain.SysRole;
import com.ruoyi.system.dto.role.req.*;
import com.ruoyi.system.vo.role.RoleVo;

/**
 * @author DavidLoman
 * @create 2025-05-11 15:26
 */
public interface RoleService {
    PageListVo<RoleVo> pageQryRoleList(RolePageQryDTO reqDTO);

    PageListVo<RoleVo> pageQryRoleListByPerms(RolePageQryDTO reqDTO);

    RoleVo getRoleInfo(Long roleId);

    Boolean addRole(RoleAddDTO roleAddDTO);

    Boolean updRole(RoleUpdDTO roleUpdDTO);

    Boolean updRoleDataScope(RoleDataScopeDTO reqDTO);

    Boolean changeStatus(RoleStatusDTO role);
}
