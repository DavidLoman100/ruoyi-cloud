package com.ruoyi.system.service.assembler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.domain.user.entity.UserQryEntity;
import com.ruoyi.system.dto.user.req.UserQryReqDTO;
import com.ruoyi.system.dto.user.req.UserUpdReqDTO;
import com.ruoyi.system.dto.user.res.UserResDTO;
import com.ruoyi.system.infrastructure.user.repository.po.SysUserPo;
import com.ruoyi.system.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-03-29 17:31
 */
@Mapper
public interface UserAssembler {
    UserAssembler INSTANCE = Mappers.getMapper(UserAssembler.class);

    UserQryEntity toUserEntity(UserQryReqDTO request);

    List<UserResDTO> toListDto(List<SysUserPo> sysUserPoPage);

    @Mapping(target = "list", source = "records")
    PageListVo<UserVo> toPageListVo(Page<SysUserPo> sysUserPage);

    //TODO 设置更新人 用户名从线程里获取
    @Mapping(target = "updateBy",expression  = "java(com.ruoyi.common.security.utils.SecurityUtils.getUsername())")
    @Mapping(target = "updateTime",expression  = "java(java.time.LocalDateTime.now())")
    SysUserPo toSysUserPO(UserUpdReqDTO userUpdReqDTO);
}
