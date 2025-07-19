package com.ruoyi.system.domain.dict.repository;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.dict.entity.DictQryEntity;
import com.ruoyi.system.infrastructure.dict.repository.po.SysDictPo;

/**
 * @author wwj
 */
public interface SysDictRepository {
    Page<SysDictPo> pageQryDictList(DictQryEntity dictQryEntity);

    Boolean hasDict(SysDictPo sysDictPo, Boolean isUpd);

    Boolean addDict(SysDictPo sysDictPo);

    Boolean updDict(SysDictPo sysDictPo);

    SysDictPo getDict(Long id);

    Boolean delDict(Long id);
}
