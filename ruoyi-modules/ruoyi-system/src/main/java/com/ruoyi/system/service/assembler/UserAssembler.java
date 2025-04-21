package com.ruoyi.system.service.assembler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListDto;
import com.ruoyi.system.domain.user.entity.UserEntity;
import com.ruoyi.system.domain.user.entity.UserQryEntity;
import com.ruoyi.system.dto.user.req.UserQryReqDTO;
import com.ruoyi.system.dto.user.req.UserUpdReqDTO;
import com.ruoyi.system.dto.user.res.UserResDTO;
import com.ruoyi.system.infrastructure.user.repository.po.SysUserPo;
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
    PageListDto<UserResDTO> toPageListDto(Page<SysUserPo> sysUserPage);

    //TODO 设置更新人 用户名从线程里获取
    @Mapping(target = "update_time",defaultValue = "java(java.time.LocalDateTime.now())")
    SysUserPo toSysUserPO(UserUpdReqDTO userUpdReqDTO);
}
