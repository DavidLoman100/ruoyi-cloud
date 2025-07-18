package com.ruoyi.system.service.assembler;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wwj
 */
@Mapper
public interface DictAssembler {

    LifeDayAssembler INSTANCE = Mappers.getMapper(LifeDayAssembler.class);

}
