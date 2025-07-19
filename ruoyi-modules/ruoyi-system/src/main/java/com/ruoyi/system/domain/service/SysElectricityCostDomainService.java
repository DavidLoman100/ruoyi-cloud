package com.ruoyi.system.domain.service;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.enums.error.CommonErrorEnum;
import com.ruoyi.common.core.enums.error.ElectricityCostErrorEnum;
import com.ruoyi.common.core.exception.BizException;
import com.ruoyi.system.domain.cost.entity.ElectricityCostExcelEntity;
import com.ruoyi.system.domain.cost.entity.ElectricityCostQryEntity;
import com.ruoyi.system.domain.cost.repository.SysElectricityCostRepository;
import com.ruoyi.system.infrastructure.cost.repository.po.SysElectricityCostPo;
import com.ruoyi.system.service.assembler.ElectricityCostAssembler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author DavidLoman
 * @create 2025-06-28 15:27
 */
@Slf4j
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

    public Boolean uploadElectricityCost(MultipartFile file) {
        List<ElectricityCostExcelEntity> entityList = new ArrayList<>();
        try(InputStream is = file.getInputStream()) {
            entityList = EasyExcel.read(is)
                    .head(ElectricityCostExcelEntity.class)
                    .sheet(0)
                    .headRowNumber(1)
                    .doReadSync();
        } catch (IOException e) {
            log.error("读取文件失败,文件名={}", file.getOriginalFilename(), e);
            throw new BizException(CommonErrorEnum.READ_FILE_ERROR);
        }
        if (entityList.isEmpty()) {
            throw new BizException(ElectricityCostErrorEnum.FILE_EMPTY_ERROR);
        }
        Set<LocalDate> liefDates = entityList.stream().map(ElectricityCostExcelEntity::getLifeDate)
                .collect(Collectors.toSet());
        if (liefDates.size() < entityList.size()) {
            throw new BizException(ElectricityCostErrorEnum.FILE_DATE_REPEAT);

        }
        Boolean isExist = sysElectricityCostRepository.hasBatchExist(new ArrayList<>(liefDates));
        if (isExist) {
            throw new BizException(ElectricityCostErrorEnum.DATE_EXIST);
        }
        List<SysElectricityCostPo> sysElectricityCostPos = ElectricityCostAssembler.INSTANCE.toElectricityPoList(entityList);
        sysElectricityCostPos.forEach(po -> {
            po.setCost(po.getPricePerKwh()
                    .multiply(po.getTotalKwh(), new MathContext(2)).setScale(2, RoundingMode.HALF_UP));
        });
        return sysElectricityCostRepository.addBatchElectricityCost(sysElectricityCostPos);
    }
}
