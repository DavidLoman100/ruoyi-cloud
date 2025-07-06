package com.ruoyi.system.service;

import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.dto.cost.req.ElectricityCostAddDTO;
import com.ruoyi.system.dto.cost.req.ElectricityCostPageQryDTO;
import com.ruoyi.system.dto.cost.req.ElectricityCostUpdDTO;
import com.ruoyi.system.vo.cost.ElectricityCostVo;
import org.springframework.web.multipart.MultipartFile;

/**
* @author 37504
* @description 针对表【sys_electricity_cost(用电支出)】的数据库操作Service
* @createDate 2025-06-28 15:11:47
*/
public interface ElectricityCostService {

    PageListVo<ElectricityCostVo> pageQryElectricityCostList(ElectricityCostPageQryDTO pageQryDTO);

    Boolean addElectricityCost(ElectricityCostAddDTO addDTO);

    Boolean updElectricityCost(ElectricityCostUpdDTO updDTO);

    Boolean uploadElectricityCost(MultipartFile file);
}
