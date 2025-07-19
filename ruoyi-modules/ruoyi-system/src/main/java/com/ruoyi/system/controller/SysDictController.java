package com.ruoyi.system.controller;

import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.common.core.commonEntity.Response;
import com.ruoyi.system.dto.dict.req.AddDictDTO;
import com.ruoyi.system.dto.dict.req.DictQryDTO;
import com.ruoyi.system.dto.dict.req.UpdDictDTO;
import com.ruoyi.system.service.DictService;
import com.ruoyi.system.vo.Dict.DictVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DavidLoman
 * @create 2025-07-19 10:07
 */
@Schema(description = "字典管理")
@RestController
@RequestMapping("/dict")
public class SysDictController {

    @Autowired
    private DictService dictService;

    @Operation(summary = "查询字典")
    @PostMapping("/list")
    private Response<PageListVo<DictVo>> pageQryDictList(@RequestParam DictQryDTO dictQryDTO) {
        return Response.ok(dictService.pageQryDictList(dictQryDTO));
    }

    @Operation(summary = "新增字典")
    @PostMapping("/add")
    private Response<Boolean> addDict(@RequestParam AddDictDTO addDictDTO) {
        return Response.ok(dictService.addDict(addDictDTO));
    }

    @Operation(summary = "编辑字典")
    @PostMapping("/edit")
    private Response<Boolean> updDict(@RequestParam UpdDictDTO updDictDTO) {
        return Response.ok(dictService.updDict(updDictDTO));
    }


    @Operation(summary = "删除字典")
    @PostMapping("/del/{id}")
    private Response<Boolean> delDict(@PathVariable Long id) {
        return Response.ok(dictService.delDict(id));
    }
}
