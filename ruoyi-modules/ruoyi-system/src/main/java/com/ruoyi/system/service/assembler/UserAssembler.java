package com.ruoyi.system.service.assembler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListDto;
import com.ruoyi.system.dto.user.UserResDTO;
import com.ruoyi.system.infrastructure.user.repository.mapper.SysUserMapper;
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

    List<UserResDTO> toListDto(List<SysUserPo> sysUserPoPage);

    @Mapping(target = "list", source = "records")
    PageListDto<UserResDTO> toPageListDto(Page<SysUserPo> sysUserPage);
}
