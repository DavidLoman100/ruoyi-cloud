package com.ruoyi.system.service.assembler;

import com.ruoyi.system.domain.menu.entity.MenuQryEntity;
import com.ruoyi.system.dto.menu.req.MenuQryReqDTO;
import com.ruoyi.system.infrastructure.menu.repository.po.SysMenuPo;
import com.ruoyi.system.vo.menu.MenuVo;
import org.mapstruct.Mapper;
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
}
