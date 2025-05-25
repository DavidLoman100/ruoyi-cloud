package com.ruoyi.system.service;

import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.api.domain.SysRole;
import com.ruoyi.system.dto.role.req.RoleAddDTO;
import com.ruoyi.system.dto.role.req.RoleDataScopeDTO;
import com.ruoyi.system.dto.role.req.RolePageQryDTO;
import com.ruoyi.system.dto.role.req.RoleUpdDTO;
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
}
