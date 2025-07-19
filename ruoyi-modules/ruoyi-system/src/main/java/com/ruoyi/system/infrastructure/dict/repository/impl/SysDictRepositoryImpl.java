package com.ruoyi.system.infrastructure.dict.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.dict.entity.DictQryEntity;
import com.ruoyi.system.domain.dict.repository.SysDictRepository;
import com.ruoyi.system.infrastructure.dict.repository.mapper.SysDictMapper;
import com.ruoyi.system.infrastructure.dict.repository.po.SysDictPo;
import com.ruoyi.system.infrastructure.life.repository.po.SysLifeDayPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author wwj
 */
@Repository
public class SysDictRepositoryImpl implements SysDictRepository {

    @Autowired
    private SysDictMapper mapper;

    @Override
    public Page<SysDictPo> pageQryDictList(DictQryEntity qryEntity) {
        Page<SysDictPo> page = new Page<>(qryEntity.getPageNum(), qryEntity.getPageSize());
        LambdaQueryWrapper<SysDictPo> wrapper = Wrappers.<SysDictPo>lambdaQuery()
                .eq(StringUtils.hasText(qryEntity.getDictCode()), SysDictPo::getDictCode, qryEntity.getDictCode())
                .eq(StringUtils.hasText(qryEntity.getDictName()), SysDictPo::getDictName, qryEntity.getDictName());
        return mapper.selectPage(page, wrapper);

    }

    @Override
    public Boolean hasDict(SysDictPo sysDictPo, Boolean isUpd) {
        Long count = mapper.selectCount(Wrappers.<SysDictPo>lambdaQuery()
                .eq(Objects.nonNull(sysDictPo.getDictCode()), SysDictPo::getDictCode, sysDictPo.getDictCode())
                .eq(Objects.nonNull(sysDictPo.getDictName()), SysDictPo::getDictName, sysDictPo.getDictName())
                .ne(isUpd, SysDictPo::getId, sysDictPo.getId()));
        return count > 0;
    }

    @Override
    public Boolean addDict(SysDictPo sysDictPo) {
        return mapper.insert(sysDictPo) > 0;
    }

    @Override
    public Boolean updDict(SysDictPo sysDictPo) {
        return mapper.updateById(sysDictPo) > 0;
    }

    @Override
    public SysDictPo getDict(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Boolean delDict(Long id) {
        return mapper.deleteById(id) > 0;
    }

}
