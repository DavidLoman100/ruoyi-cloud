package com.ruoyi.system.controller;

import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.common.core.commonEntity.Response;
import com.ruoyi.system.dto.lifeDay.req.AddLifeDayDTO;
import com.ruoyi.system.dto.lifeDay.req.LifeDayQryDTO;
import com.ruoyi.system.dto.lifeDay.req.UpdLifeDayDTO;
import com.ruoyi.system.service.LifeDayService;
import com.ruoyi.system.vo.lifeDay.LifeDayVo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DavidLoman
 * @create 2025-06-14 16:58
 */
@RestController
@RequestMapping("/lifeDay")
public class SysLifeDayController {


    @Autowired
    private LifeDayService lifeDayService;

    @Operation(summary = "查询生活日表列表")
    @PostMapping("/page/list")
    public Response<PageListVo<LifeDayVo>> pageQryLifeDayList(@RequestBody LifeDayQryDTO lifeDayQryDTO) {
        return Response.ok(lifeDayService.pageQryLifeDayList(lifeDayQryDTO));
    }

    @Operation(summary = "新增生活日表列表")
    @PostMapping("/add")
    public Response<Boolean> addLifeDay(@RequestBody @Validated AddLifeDayDTO addLifeDayDTO) {
        return Response.ok(lifeDayService.addLifeDay(addLifeDayDTO));
    }

    @Operation(summary = "编辑生活日表列表")
    @PostMapping("/edit")
    public Response<Boolean> updLifeDay(@RequestBody @Validated UpdLifeDayDTO updLifeDayDTO) {
        return Response.ok(lifeDayService.updLifeDay(updLifeDayDTO));
    }



}
