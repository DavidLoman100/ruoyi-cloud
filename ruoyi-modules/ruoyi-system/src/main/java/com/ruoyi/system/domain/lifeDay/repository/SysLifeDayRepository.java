package com.ruoyi.system.domain.lifeDay.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.lifeDay.entity.LifeDayQryEntity;
import com.ruoyi.system.infrastructure.life.repository.po.SysLifeDayPo;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author DavidLoman
 * @create 2025-06-14 21:22
 */
public interface SysLifeDayRepository {
    Page<SysLifeDayPo> pageQryLifeDayList(LifeDayQryEntity qryEntity);

    SysLifeDayPo getLifeDay(Long id);

    Boolean addLifeDay(SysLifeDayPo sysLifeDayPo);

    Boolean updLifeDay(SysLifeDayPo sysLifeDayPo);

    Boolean hasLifeDay(LocalDate lifeDate);
}
