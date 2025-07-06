package com.ruoyi.system.domain.cost.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.domain.cost.entity.ElectricityCostQryEntity;
import com.ruoyi.system.infrastructure.cost.repository.po.SysElectricityCostPo;
import com.ruoyi.system.vo.cost.ElectricityCostVo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-06-28 15:12
 */
public interface SysElectricityCostRepository {
    Page<SysElectricityCostPo> pageQryElectricityCostList(ElectricityCostQryEntity electricityCostQryEntity);

    SysElectricityCostPo getById(Long id);

    Boolean hasExist(LocalDate lifeDate);

    Boolean hasBatchExist(List<LocalDate> lifeDates);

    Boolean addElectricityCost(SysElectricityCostPo sysElectricityCostPo);

    Boolean updElectricityCost(SysElectricityCostPo sysElectricityCostPo);

    Boolean addBatchElectricityCost(List<SysElectricityCostPo> sysElectricityCostPos);
}
