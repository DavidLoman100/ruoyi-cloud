package com.ruoyi.system.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.common.core.enums.error.CommonErrorEnum;
import com.ruoyi.common.core.enums.error.ElectricityCostErrorEnum;
import com.ruoyi.common.core.exception.BizException;
import com.ruoyi.system.domain.cost.entity.ElectricityCostQryEntity;
import com.ruoyi.system.domain.cost.repository.SysElectricityCostRepository;
import com.ruoyi.system.infrastructure.cost.repository.po.SysElectricityCostPo;
import com.ruoyi.system.vo.cost.ElectricityCostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author DavidLoman
 * @create 2025-06-28 15:27
 */
@Service
public class SysElectricityCostDomainService {

    @Autowired
    private SysElectricityCostRepository sysElectricityCostRepository;

    public Page<SysElectricityCostPo> pageQryElectricityCostList(ElectricityCostQryEntity qryEntity) {
        return sysElectricityCostRepository.pageQryElectricityCostList(qryEntity);
    }

    public Boolean addElectricityCost(SysElectricityCostPo sysElectricityCostPo) {
        Boolean isExist = sysElectricityCostRepository.hasExist(sysElectricityCostPo.getLifeDate());
        if (isExist) {
            throw new BizException(ElectricityCostErrorEnum.DATE_EXIST);
        }
        BigDecimal cost = sysElectricityCostPo.getPricePerKwh().multiply(sysElectricityCostPo.getTotalKwh());
        sysElectricityCostPo.setCost(cost);
        return sysElectricityCostRepository.addElectricityCost(sysElectricityCostPo);
    }

    public Boolean updElectricityCost(SysElectricityCostPo sysElectricityCostPo) {
        SysElectricityCostPo sysElectricityCostPoDb = sysElectricityCostRepository.getById(sysElectricityCostPo.getId());
        if (Objects.isNull(sysElectricityCostPoDb)) {
            throw new BizException(CommonErrorEnum.INVALID_INFO);
        }
        return sysElectricityCostRepository.updElectricityCost(sysElectricityCostPo);
    }
}
