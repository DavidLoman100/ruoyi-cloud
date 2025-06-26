package com.ruoyi.system.infrastructure.dept.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.lifeDay.entity.LifeDayQryEntity;
import com.ruoyi.system.domain.lifeDay.repository.SysLifeDayRepository;
import com.ruoyi.system.infrastructure.life.repository.mapper.SysLifeDayMapper;
import com.ruoyi.system.infrastructure.life.repository.po.SysLifeDayPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * @author DavidLoman
 * @create 2025-06-14 21:24
 */
@Repository
public class SysLifeDayRepositoryImpl implements SysLifeDayRepository {

    @Autowired
    private SysLifeDayMapper sysLifeDayMapper;
    @Override
    public Page<SysLifeDayPo> pageQryLifeDayList(LifeDayQryEntity qryEntity) {
        Page<SysLifeDayPo> page = new Page<>(qryEntity.getPageNum(), qryEntity.getPageSize());
        LambdaQueryWrapper<SysLifeDayPo> wrapper = Wrappers.<SysLifeDayPo>lambdaQuery()
                .ge(!Objects.isNull(qryEntity.getStartLifeDate()), SysLifeDayPo::getLifeDate, qryEntity.getStartLifeDate())
                .le(!Objects.isNull(qryEntity.getEndLifeDate()), SysLifeDayPo::getLifeDate, qryEntity.getEndLifeDate())
                .orderByDesc(SysLifeDayPo::getLifeDate);
        Page<SysLifeDayPo> result = sysLifeDayMapper.selectPage(page, wrapper);
        return result;

    }

    @Override
    public SysLifeDayPo getLifeDay(Long id) {
        return sysLifeDayMapper.selectById(id);
    }

    @Override
    public Boolean addLifeDay(SysLifeDayPo sysLifeDayPo) {
        return sysLifeDayMapper.insert(sysLifeDayPo) > 0 ? true : false;
    }

    @Override
    public Boolean updLifeDay(SysLifeDayPo sysLifeDayPo) {
        return sysLifeDayMapper.updateById(sysLifeDayPo) > 0 ? true : false;
    }

    @Override
    public Boolean hasLifeDay(LocalDate lifeDate) {
        return sysLifeDayMapper.selectCount(Wrappers.<SysLifeDayPo>lambdaQuery()
                .eq(SysLifeDayPo::getLifeDate, lifeDate))>0 ? true : false;
    }

}
