package com.ruoyi.system.service;

import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.dto.lifeDay.req.AddLifeDayDTO;
import com.ruoyi.system.dto.lifeDay.req.LifeDayQryDTO;
import com.ruoyi.system.dto.lifeDay.req.UpdLifeDayDTO;
import com.ruoyi.system.vo.lifeDay.LifeDayVo;

/**
* @author 37504
* @description 针对表【sys_life_day(生活日表)】的数据库操作Service
* @createDate 2025-06-14 15:49:33
*/
public interface LifeDayService {

    PageListVo<LifeDayVo> pageQryLifeDayList(LifeDayQryDTO lifeDayQryDTO);

    Boolean addLifeDay(AddLifeDayDTO addLifeDayDTO);

    Boolean updLifeDay(UpdLifeDayDTO updLifeDayDTO);
}
