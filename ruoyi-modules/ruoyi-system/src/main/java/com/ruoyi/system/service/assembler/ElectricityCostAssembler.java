package com.ruoyi.system.service.assembler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.domain.cost.entity.ElectricityCostQryEntity;
import com.ruoyi.system.dto.cost.req.ElectricityCostAddDTO;
import com.ruoyi.system.dto.cost.req.ElectricityCostPageQryDTO;
import com.ruoyi.system.dto.cost.req.ElectricityCostUpdDTO;
import com.ruoyi.system.infrastructure.cost.repository.po.SysElectricityCostPo;
import com.ruoyi.system.vo.cost.ElectricityCostVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author DavidLoman
 * @create 2025-06-28 16:01
 */
@Mapper
public interface ElectricityCostAssembler {

    ElectricityCostAssembler INSTANCE = Mappers.getMapper(ElectricityCostAssembler.class);

    ElectricityCostQryEntity toElectricityCostEntity(ElectricityCostPageQryDTO qryDTO);

    @Mapping(target = "list", source = "records")
    PageListVo<ElectricityCostVo> toPageListVo(Page<SysElectricityCostPo> result);

    @Mappings({
            @Mapping(target = "createBy",expression = "java(com.ruoyi.common.security.utils.SecurityUtils.getUsername())"),
            @Mapping(target = "createTime",expression = "java(java.time.LocalDateTime.now())")
    })
    SysElectricityCostPo toElectricityPo(ElectricityCostAddDTO addDTO);

    @Mappings({
            @Mapping(target = "updateBy",expression = "java(com.ruoyi.common.security.utils.SecurityUtils.getUsername())"),
            @Mapping(target = "updateTime",expression = "java(java.time.LocalDateTime.now())")
    })
    SysElectricityCostPo toElectricityPo(ElectricityCostUpdDTO updDTO);
}
