package com.ruoyi.system.infrastructure.cost.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.domain.cost.entity.ElectricityCostQryEntity;
import com.ruoyi.system.domain.cost.repository.SysElectricityCostRepository;
import com.ruoyi.system.infrastructure.cost.repository.mapper.SysElectricityCostMapper;
import com.ruoyi.system.infrastructure.cost.repository.po.SysElectricityCostPo;
import com.ruoyi.system.infrastructure.life.repository.po.SysLifeDayPo;
import com.ruoyi.system.vo.cost.ElectricityCostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author DavidLoman
 * @create 2025-06-28 15:24
 */
@Repository
public class SysElectricityCostRepositoryImpl implements SysElectricityCostRepository {
    @Autowired
    private SysElectricityCostMapper mapper;
    @Override
    public Page<SysElectricityCostPo> pageQryElectricityCostList(ElectricityCostQryEntity qryEntity) {
        Page<SysElectricityCostPo> page = new Page<>(qryEntity.getPageNum(), qryEntity.getPageSize());
        LambdaQueryWrapper<SysElectricityCostPo> wrapper = Wrappers.<SysElectricityCostPo>lambdaQuery()
                .ge(!Objects.isNull(qryEntity.getStartLifeDate()), SysElectricityCostPo::getLifeDate, qryEntity.getStartLifeDate())
                .le(!Objects.isNull(qryEntity.getEndLifeDate()), SysElectricityCostPo::getLifeDate, qryEntity.getEndLifeDate())
                .orderByDesc(SysElectricityCostPo::getLifeDate);
        return mapper.selectPage(page, wrapper);
    }

    @Override
    public SysElectricityCostPo getById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Boolean hasExist(LocalDate lifeDate) {
        Long count = mapper.selectCount(Wrappers.<SysElectricityCostPo>lambdaQuery()
                .eq(SysElectricityCostPo::getLifeDate, lifeDate));
        return count>0 ? true : false;
    }

    @Override
    public Boolean hasBatchExist(List<LocalDate> lifeDates) {
        Long count = mapper.selectCount(Wrappers.<SysElectricityCostPo>lambdaQuery()
                .in(SysElectricityCostPo::getLifeDate, lifeDates));
        return count > 0 ? true : false;
    }

    @Override
    public Boolean addElectricityCost(SysElectricityCostPo sysElectricityCostPo) {
        return mapper.insert(sysElectricityCostPo) > 0 ? true : false;
    }

    @Override
    public Boolean updElectricityCost(SysElectricityCostPo sysElectricityCostPo) {
        return mapper.updateById(sysElectricityCostPo) > 0 ? true : false;
    }

    @Override
    public Boolean addBatchElectricityCost(List<SysElectricityCostPo> sysElectricityCostPos) {
        int insertCount = mapper.insertBatch(sysElectricityCostPos);
        return insertCount == sysElectricityCostPos.size() ? true : false;
    }


}
