package com.ruoyi.system.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.lifeDay.entity.LifeDayQryEntity;
import com.ruoyi.system.domain.lifeDay.repository.SysLifeDayRepository;
import com.ruoyi.system.infrastructure.life.repository.po.SysLifeDayPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author DavidLoman
 * @create 2025-06-14 21:22
 */
@Service
public class SysLifeDayDomainService {

    @Autowired
    private SysLifeDayRepository sysLifeDayRepository;
    public Page<SysLifeDayPo> pageQryLifeDayList(LifeDayQryEntity qryEntity) {
        return sysLifeDayRepository.pageQryLifeDayList(qryEntity);

    }

    public SysLifeDayPo getLifeDay(Long id) {
        return sysLifeDayRepository.getLifeDay(id);
    }

    public Boolean addLifeDay(SysLifeDayPo sysLifeDayPo) {
        return sysLifeDayRepository.addLifeDay(sysLifeDayPo);
    }

    public Boolean updLifeDay(SysLifeDayPo sysLifeDayPo) {
        return sysLifeDayRepository.updLifeDay(sysLifeDayPo);
    }


    public Boolean hasLifeDay(LocalDate lifeDate) {
        return sysLifeDayRepository.hasLifeDay(lifeDate);
    }

}
