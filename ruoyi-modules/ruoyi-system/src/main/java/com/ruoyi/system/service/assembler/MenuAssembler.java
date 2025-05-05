package com.ruoyi.system.service.assembler;

import com.ruoyi.system.domain.menu.entity.MenuQryEntity;
import com.ruoyi.system.dto.menu.req.MenuAddDTO;
import com.ruoyi.system.dto.menu.req.MenuQryReqDTO;
import com.ruoyi.system.dto.menu.req.MenuUpdDTO;
import com.ruoyi.system.infrastructure.menu.repository.po.SysMenuPo;
import com.ruoyi.system.vo.menu.MenuTreeVo;
import com.ruoyi.system.vo.menu.MenuVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-05-01 1:40
 */
@Mapper
public interface MenuAssembler {
    MenuAssembler INSTANCE = Mappers.getMapper(MenuAssembler.class);

    MenuQryEntity toMenuQryEntity(MenuQryReqDTO reqDTO);

    List<MenuVo> toMenuVoList(List<SysMenuPo> sysMenuPos);

    MenuVo toMenuVo(SysMenuPo sysMenuPo);

    MenuTreeVo toMenuTreeVo(SysMenuPo sysMenuPo);
    List<MenuTreeVo> toMenuTreeVos(List<SysMenuPo> sysMenuPos);

    @Mappings({
            @Mapping(target = "createBy",expression = "java(com.ruoyi.common.security.utils.SecurityUtils.getUsername())"),
            @Mapping(target = "createTime",expression = "java(java.time.LocalDateTime.now())")
    })
    SysMenuPo toSysMenuPo(MenuAddDTO addDTO);

    @Mappings({
            @Mapping(target = "updateBy",expression = "java(com.ruoyi.common.security.utils.SecurityUtils.getUsername())"),
            @Mapping(target = "updateTime",expression = "java(java.time.LocalDateTime.now())")
    })
    SysMenuPo toSysMenuPo(MenuUpdDTO updDTO);

}
