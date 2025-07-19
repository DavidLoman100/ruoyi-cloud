package com.ruoyi.system.service;

import com.ruoyi.common.core.commonEntity.PageListVo;
import com.ruoyi.system.dto.dict.req.AddDictDTO;
import com.ruoyi.system.dto.dict.req.DictQryDTO;
import com.ruoyi.system.dto.dict.req.UpdDictDTO;
import com.ruoyi.system.vo.Dict.DictVo;

/**
 * @author wwj
 */
public interface DictService {
    PageListVo<DictVo> pageQryDictList(DictQryDTO dictQryDTO);

    Boolean addDict(AddDictDTO addDictDTO);

    Boolean updDict(UpdDictDTO updDictDTO);

    Boolean delDict(Long id);
}
