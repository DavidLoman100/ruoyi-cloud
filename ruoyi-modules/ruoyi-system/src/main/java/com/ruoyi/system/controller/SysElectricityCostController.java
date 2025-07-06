package com.ruoyi.system.controller;

import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.common.core.commonEntity.Response;
import com.ruoyi.system.dto.cost.req.ElectricityCostAddDTO;
import com.ruoyi.system.dto.cost.req.ElectricityCostPageQryDTO;
import com.ruoyi.system.dto.cost.req.ElectricityCostUpdDTO;
import com.ruoyi.system.service.ElectricityCostService;
import com.ruoyi.system.vo.cost.ElectricityCostVo;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DavidLoman
 * @create 2025-06-28 15:30
 */
@RestController
@RequestMapping("/electricityCost")
public class SysElectricityCostController {

    @Autowired
    private ElectricityCostService electricityCostService;

    @Operation(summary = "分页查询电费信息")
    @PostMapping("/page/list")
    public Response<PageListVo<ElectricityCostVo>> pageQryElectricityCostList(@RequestBody ElectricityCostPageQryDTO pageQryDTO) {
        PageListVo<ElectricityCostVo> pageListVo = electricityCostService.pageQryElectricityCostList(pageQryDTO);
        return Response.ok(pageListVo);
    }

    @Operation(summary = "新增电费信息")
    @PostMapping("/add")
    public Response<Boolean> addElectricityCost(@RequestBody @Validated ElectricityCostAddDTO addDTO) {
        return Response.ok(electricityCostService.addElectricityCost(addDTO));
    }

    @Operation(summary = "修改电费信息")
    @PostMapping("/edit")
    public Response<Boolean> updElectricityCost(@RequestBody @Validated ElectricityCostUpdDTO updDTO) {
        return Response.ok(electricityCostService.updElectricityCost(updDTO));
    }

    @Operation(summary = "文件导入电费信息")
    @PostMapping("/upload")
    public Response<Boolean> uploadElectricityCost(@RequestPart("file") MultipartFile file) {
        return Response.ok(electricityCostService.uploadElectricityCost(file));
    }


}
