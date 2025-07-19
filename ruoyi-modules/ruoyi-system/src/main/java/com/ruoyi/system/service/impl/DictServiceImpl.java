package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.domain.dict.entity.DictEntity;
import com.ruoyi.system.domain.dict.entity.DictQryEntity;
import com.ruoyi.system.domain.service.SysDictDomainService;
import com.ruoyi.system.dto.dict.req.AddDictDTO;
import com.ruoyi.system.dto.dict.req.DictQryDTO;
import com.ruoyi.system.dto.dict.req.UpdDictDTO;
import com.ruoyi.system.infrastructure.dict.repository.po.SysDictPo;
import com.ruoyi.system.service.DictService;
import com.ruoyi.system.service.assembler.DictAssembler;
import com.ruoyi.system.vo.Dict.DictVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wwj
 */
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private SysDictDomainService dictDomainService;
    @Override
    public PageListVo<DictVo> pageQryDictList(DictQryDTO dictQryDTO) {
        DictQryEntity dictQryEntity = DictAssembler.INSTANCE.toDictQryEntity(dictQryDTO);
        Page<SysDictPo> sysDictPoPage = dictDomainService.pageQryDictList(dictQryEntity);
        PageListVo<DictVo> pageListVo = DictAssembler.INSTANCE.toPageListVo(sysDictPoPage);
        return pageListVo;
    }

    @Override
    public Boolean addDict(AddDictDTO addDictDTO) {
        SysDictPo sysDictPo = DictAssembler.INSTANCE.toSysDictPo(addDictDTO);
        return dictDomainService.addDict(sysDictPo);
    }

    @Override
    public Boolean updDict(UpdDictDTO updDictDTO) {
        SysDictPo sysDictPo = DictAssembler.INSTANCE.toSysDictPo(updDictDTO);
        return dictDomainService.updDict(sysDictPo);
    }

    @Override
    public Boolean delDict(Long id) {
        return dictDomainService.delDict(id);
    }
}
