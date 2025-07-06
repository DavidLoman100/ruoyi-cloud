package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.domain.cost.entity.ElectricityCostQryEntity;
import com.ruoyi.system.domain.service.SysElectricityCostDomainService;
import com.ruoyi.system.dto.cost.req.ElectricityCostAddDTO;
import com.ruoyi.system.dto.cost.req.ElectricityCostPageQryDTO;
import com.ruoyi.system.dto.cost.req.ElectricityCostUpdDTO;
import com.ruoyi.system.infrastructure.cost.repository.po.SysElectricityCostPo;
import com.ruoyi.system.service.ElectricityCostService;
import com.ruoyi.system.service.assembler.ElectricityCostAssembler;
import com.ruoyi.system.vo.cost.ElectricityCostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 37504
 * @description 针对表【sys_electricity_cost(用电支出)】的数据库操作Service实现
 * @createDate 2025-06-28 15:11:47
 */
@Service
public class ElectricityCostServiceImpl implements ElectricityCostService {


    @Autowired
    private SysElectricityCostDomainService electricityCostDomainService;

    @Override
    public PageListVo<ElectricityCostVo> pageQryElectricityCostList(ElectricityCostPageQryDTO pageQryDTO) {
        ElectricityCostQryEntity qryEntity = ElectricityCostAssembler.INSTANCE.toElectricityCostEntity(pageQryDTO);
        Page<SysElectricityCostPo> result = electricityCostDomainService.pageQryElectricityCostList(qryEntity);
        PageListVo<ElectricityCostVo> pageListVo = ElectricityCostAssembler.INSTANCE.toPageListVo(result);
        return pageListVo;
    }

    @Override
    public Boolean addElectricityCost(ElectricityCostAddDTO addDTO) {
        SysElectricityCostPo sysElectricityCostPo = ElectricityCostAssembler.INSTANCE.toElectricityPo(addDTO);
        return electricityCostDomainService.addElectricityCost(sysElectricityCostPo);
    }

    @Override
    public Boolean updElectricityCost(ElectricityCostUpdDTO updDTO) {
        SysElectricityCostPo sysElectricityCostPo = ElectricityCostAssembler.INSTANCE.toElectricityPo(updDTO);
        return electricityCostDomainService.updElectricityCost(sysElectricityCostPo);
    }

    @Override
    public Boolean uploadElectricityCost(MultipartFile file) {
        return electricityCostDomainService.uploadElectricityCost(file);
    }

}




