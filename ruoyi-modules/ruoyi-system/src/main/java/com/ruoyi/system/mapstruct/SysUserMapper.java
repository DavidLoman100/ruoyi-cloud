package com.ruoyi.system.mapstruct;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListDto;
import com.ruoyi.system.dto.SysUserDto;
import com.ruoyi.system.entity.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-03-29 17:31
 */
@Mapper
public interface SysUserMapper {
    SysUserMapper INSTANCE = Mappers.getMapper(SysUserMapper.class);

    List<SysUserDto> toListDto(List<SysUser> sysUserPage);

    @Mapping(target = "list", source = "records")
    PageListDto<SysUserDto> toPageListDto(Page<SysUser> sysUserPage);
}
