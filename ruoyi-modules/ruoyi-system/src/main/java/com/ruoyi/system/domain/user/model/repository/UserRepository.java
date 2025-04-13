package com.ruoyi.system.domain.user.model.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.infrastructure.user.repository.po.SysUserPo;
import com.ruoyi.system.dto.user.UserQryReqDTO;

/**
 * @author DavidLoman
 * @create 2025-04-14 1:13
 */
public interface UserRepository {

    Page<SysUserPo> pageQrySysUser(UserQryReqDTO request);
}
