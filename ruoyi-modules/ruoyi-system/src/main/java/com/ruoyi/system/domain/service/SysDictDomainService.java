package com.ruoyi.system.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.enums.error.CommonErrorEnum;
import com.ruoyi.common.core.enums.error.DictErrorEnum;
import com.ruoyi.common.core.enums.error.RoleErrorEnum;
import com.ruoyi.common.core.exception.BizException;
import com.ruoyi.system.domain.dict.entity.DictQryEntity;
import com.ruoyi.system.domain.dict.repository.SysDictRepository;
import com.ruoyi.system.infrastructure.dict.repository.po.SysDictPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author DavidLoman
 * @create 2025-07-19 11:51
 */
@Service
public class SysDictDomainService {

    @Autowired
    private SysDictRepository sysDictRepository;

    public Page<SysDictPo> pageQryDictList(DictQryEntity dictQryEntity) {
        return sysDictRepository.pageQryDictList(dictQryEntity);
    }

    public Boolean addDict(SysDictPo sysDictPo) {
        Boolean exist = sysDictRepository.hasDict(sysDictPo, false);
        if (exist) {
            throw new BizException(DictErrorEnum.CODE_REPEAT);
        }
        return sysDictRepository.addDict(sysDictPo);
    }

    public Boolean updDict(SysDictPo sysDictPo) {
        SysDictPo dictPoDb = sysDictRepository.getDict(sysDictPo.getId());
        if (Objects.isNull(dictPoDb)) {
            throw new BizException(CommonErrorEnum.INVALID_INFO);
        }
        Boolean exist = sysDictRepository.hasDict(sysDictPo, true);
        if (exist) {
            throw new BizException(DictErrorEnum.CODE_REPEAT);
        }
        return sysDictRepository.updDict(sysDictPo);
    }

    public Boolean delDict(Long id) {
        SysDictPo dictPoDb = sysDictRepository.getDict(id);
        if (Objects.isNull(dictPoDb)) {
            throw new BizException(CommonErrorEnum.INVALID_INFO);
        }
        return sysDictRepository.delDict(id);
    }
}
