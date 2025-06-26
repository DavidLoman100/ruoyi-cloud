package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.common.core.enums.error.CommonErrorEnum;
import com.ruoyi.common.core.enums.error.LifeDayErrorEnum;
import com.ruoyi.common.core.exception.BizException;
import com.ruoyi.system.domain.lifeDay.entity.LifeDayQryEntity;
import com.ruoyi.system.domain.service.SysLifeDayDomainService;
import com.ruoyi.system.dto.lifeDay.req.AddLifeDayDTO;
import com.ruoyi.system.dto.lifeDay.req.LifeDayQryDTO;
import com.ruoyi.system.dto.lifeDay.req.UpdLifeDayDTO;
import com.ruoyi.system.infrastructure.life.repository.po.SysLifeDayPo;
import com.ruoyi.system.service.LifeDayService;
import com.ruoyi.system.service.assembler.LifeDayAssembler;
import com.ruoyi.system.vo.lifeDay.LifeDayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 37504
 * @description 针对表【sys_life_day(生活日表)】的数据库操作Service实现
 * @createDate 2025-06-14 15:49:33
 */
@Service
public class LifeDayServiceImpl implements LifeDayService {


    @Autowired
    private SysLifeDayDomainService sysLifeDayDomainService;

    @Override
    public PageListVo<LifeDayVo> pageQryLifeDayList(LifeDayQryDTO qryDTO) {
        LifeDayQryEntity qryEntity = LifeDayAssembler.INSTANCE.toLifeDayEntity(qryDTO);
        Page<SysLifeDayPo> lifeDayPoPage = sysLifeDayDomainService.pageQryLifeDayList(qryEntity);
        PageListVo<LifeDayVo> lifeDayVoPage = LifeDayAssembler.INSTANCE.toPageListVo(lifeDayPoPage);
        return lifeDayVoPage;

    }

    @Override
    public Boolean addLifeDay(AddLifeDayDTO addLifeDayDTO) {
        Boolean isExist = sysLifeDayDomainService.hasLifeDay(addLifeDayDTO.getLifeDate());
        if (isExist) {
            throw new BizException(LifeDayErrorEnum.DATE_EXIST);
        }
        
        SysLifeDayPo sysLifeDayPo = LifeDayAssembler.INSTANCE.toLifeDayPo(addLifeDayDTO);
        return sysLifeDayDomainService.addLifeDay(sysLifeDayPo);
    }

    @Override
    public Boolean updLifeDay(UpdLifeDayDTO updLifeDayDTO) {
        SysLifeDayPo lifeDayPoDb = sysLifeDayDomainService.getLifeDay(updLifeDayDTO.getId());
        if (Objects.isNull(lifeDayPoDb)) {
            throw new BizException(CommonErrorEnum.INVALID_INFO);
        }
        SysLifeDayPo sysLifeDayPo = LifeDayAssembler.INSTANCE.toLifeDayPo(updLifeDayDTO);
        return sysLifeDayDomainService.updLifeDay(sysLifeDayPo);
    }
}




