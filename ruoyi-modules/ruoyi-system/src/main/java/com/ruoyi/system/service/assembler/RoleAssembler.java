package com.ruoyi.system.service.assembler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.domain.role.entity.RolePageQryEntity;
import com.ruoyi.system.dto.role.req.RoleAddDTO;
import com.ruoyi.system.dto.role.req.RoleDataScopeDTO;
import com.ruoyi.system.dto.role.req.RolePageQryDTO;
import com.ruoyi.system.dto.role.req.RoleUpdDTO;
import com.ruoyi.system.infrastructure.role.repository.po.SysRolePo;
import com.ruoyi.system.vo.role.RoleVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author DavidLoman
 * @create 2025-05-11 15:29
 */
@Mapper
public interface RoleAssembler {
    RoleAssembler INSTANCE = Mappers.getMapper(RoleAssembler.class);

    RolePageQryEntity toRolePageQryEntity(RolePageQryDTO reqDTO);

    @Mapping(target = "list", source = "records")
    PageListVo<RoleVo> toPageListVo(Page<SysRolePo> page);

    RoleVo toRoleVo(SysRolePo roleByPerms);

    @Mappings({
            @Mapping(target = "createBy",expression = "java(com.ruoyi.common.security.utils.SecurityUtils.getUsername())"),
            @Mapping(target = "createTime",expression = "java(java.time.LocalDateTime.now())")
    })
    SysRolePo toSysRolePo(RoleAddDTO roleAddDTO);

    @Mappings({
            @Mapping(target = "updateBy",expression = "java(com.ruoyi.common.security.utils.SecurityUtils.getUsername())"),
            @Mapping(target = "updateTime",expression = "java(java.time.LocalDateTime.now())")
    })
    SysRolePo toSysRolePo(RoleUpdDTO roleUpdDTO);

    @Mappings({
            @Mapping(target = "updateBy",expression = "java(com.ruoyi.common.security.utils.SecurityUtils.getUsername())"),
            @Mapping(target = "updateTime",expression = "java(java.time.LocalDateTime.now())")
    })
    SysRolePo toSysRolePo(RoleDataScopeDTO reqDTO);
}
