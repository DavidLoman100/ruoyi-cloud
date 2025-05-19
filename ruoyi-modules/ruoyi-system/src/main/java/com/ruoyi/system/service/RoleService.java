package com.ruoyi.system.service;

import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.dto.role.RolePageQryDTO;
import com.ruoyi.system.vo.role.RoleVo;

/**
 * @author DavidLoman
 * @create 2025-05-11 15:26
 */
public interface RoleService {
    PageListVo<RoleVo> pageQryRoleList(RolePageQryDTO reqDTO);

    PageListVo<RoleVo> pageQryRoleListByPerms(RolePageQryDTO reqDTO);


}
