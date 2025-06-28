package com.ruoyi.system.controller;

import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.common.core.commonEntity.Response;
import com.ruoyi.system.dto.cost.req.ElectricityCostAddDTO;
import com.ruoyi.system.dto.cost.req.ElectricityCostPageQryDTO;
import com.ruoyi.system.dto.cost.req.ElectricityCostUpdDTO;
import com.ruoyi.system.service.ElectricityCostService;
import com.ruoyi.system.vo.cost.ElectricityCostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DavidLoman
 * @create 2025-06-28 15:30
 */
@RestController
@RequestMapping("/electricityCost")
public class SysElectricityCostController {

    @Autowired
    private ElectricityCostService electricityCostService;

    @PostMapping("/page/list")
    public Response<PageListVo<ElectricityCostVo>> pageQryElectricityCostList(@RequestBody ElectricityCostPageQryDTO pageQryDTO) {
        PageListVo<ElectricityCostVo> pageListVo = electricityCostService.pageQryElectricityCostList(pageQryDTO);
        return Response.ok(pageListVo);
    }

    @PostMapping("/add")
    public Response<Boolean> addElectricityCost(@RequestBody @Validated ElectricityCostAddDTO addDTO) {
        return Response.ok(electricityCostService.addElectricityCost(addDTO));
    }

    @PostMapping("/edit")
    public Response<Boolean> updElectricityCost(@RequestBody @Validated ElectricityCostUpdDTO updDTO) {
        return Response.ok(electricityCostService.updElectricityCost(updDTO));
    }


}
