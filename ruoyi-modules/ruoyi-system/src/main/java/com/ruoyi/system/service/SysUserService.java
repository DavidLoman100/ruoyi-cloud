package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.commonEntity.PageListDto;
import com.ruoyi.system.dto.user.UserResDTO;
import com.ruoyi.system.infrastructure.user.repository.po.SysUserPo;
import com.ruoyi.system.dto.user.UserQryReqDTO;

/**
* @author 37504
* @description 针对表【sys_user(用户信息表)】的数据库操作Service
* @createDate 2025-03-21 00:09:01
*/
public interface SysUserService extends IService<SysUserPo>{

    PageListDto<UserResDTO> pageQrySysUser(UserQryReqDTO request);

    Boolean updUserInfo();
}
