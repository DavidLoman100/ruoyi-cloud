package com.ruoyi.system.service.assembler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.domain.dict.entity.DictEntity;
import com.ruoyi.system.domain.dict.entity.DictQryEntity;
import com.ruoyi.system.dto.dict.req.AddDictDTO;
import com.ruoyi.system.dto.dict.req.DictQryDTO;
import com.ruoyi.system.dto.dict.req.UpdDictDTO;
import com.ruoyi.system.infrastructure.dict.repository.po.SysDictPo;
import com.ruoyi.system.vo.Dict.DictVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author wwj
 */
@Mapper
public interface DictAssembler {

    DictAssembler INSTANCE = Mappers.getMapper(DictAssembler.class);

    DictQryEntity toDictQryEntity(DictQryDTO dictQryDTO);

    @Mapping(target = "list", source = "records")
    PageListVo<DictVo> toPageListVo(Page<SysDictPo> sysDictPoPage);

    @Mappings({
            @Mapping(target = "createBy",expression = "java(com.ruoyi.common.security.utils.SecurityUtils.getUsername())"),
            @Mapping(target = "createTime",expression = "java(java.time.LocalDateTime.now())")
    })
    SysDictPo toSysDictPo(AddDictDTO addDictDTO);

    @Mappings({
            @Mapping(target = "updateBy",expression = "java(com.ruoyi.common.security.utils.SecurityUtils.getUsername())"),
            @Mapping(target = "updateTime",expression = "java(java.time.LocalDateTime.now())")
    })
    SysDictPo toSysDictPo(UpdDictDTO updDictDTO);

}
