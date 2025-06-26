package com.ruoyi.system.service.assembler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.domain.lifeDay.entity.LifeDayQryEntity;
import com.ruoyi.system.dto.lifeDay.req.AddLifeDayDTO;
import com.ruoyi.system.dto.lifeDay.req.LifeDayQryDTO;
import com.ruoyi.system.dto.lifeDay.req.UpdLifeDayDTO;
import com.ruoyi.system.infrastructure.life.repository.po.SysLifeDayPo;
import com.ruoyi.system.vo.lifeDay.LifeDayVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author DavidLoman
 * @create 2025-06-14 21:37
 */
@Mapper
public interface LifeDayAssembler {
    LifeDayAssembler INSTANCE = Mappers.getMapper(LifeDayAssembler.class);

    LifeDayQryEntity toLifeDayEntity(LifeDayQryDTO request);

    @Mapping(target = "list", source = "records")
    PageListVo<LifeDayVo> toPageListVo(Page<SysLifeDayPo> lifeDayPoPage);

    @Mappings({
            @Mapping(target = "createBy",expression = "java(com.ruoyi.common.security.utils.SecurityUtils.getUsername())"),
            @Mapping(target = "createTime",expression = "java(java.time.LocalDateTime.now())")
    })
    SysLifeDayPo toLifeDayPo(AddLifeDayDTO addLifeDayDTO);

    @Mappings({
            @Mapping(target = "updateBy",expression = "java(com.ruoyi.common.security.utils.SecurityUtils.getUsername())"),
            @Mapping(target = "updateTime",expression = "java(java.time.LocalDateTime.now())")
    })
    SysLifeDayPo toLifeDayPo(UpdLifeDayDTO updLifeDayDTO);
}
