package com.ruoyi.system.service;

import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.dto.user.req.UserUpdReqDTO;
import com.ruoyi.system.dto.user.res.UserResDTO;
import com.ruoyi.system.dto.user.req.UserQryReqDTO;
import com.ruoyi.system.vo.UserVo;

/**
* @author 37504
* @description 针对表【sys_user(用户信息表)】的数据库操作Service
* @createDate 2025-03-21 00:09:01
*/
public interface SysUserService {

    PageListVo<UserVo> pageQrySysUser(UserQryReqDTO request);

    Boolean updUserInfo(UserUpdReqDTO userUpdReqDTO);
}
