package com.ruoyi.common.core.exception;

import com.ruoyi.common.core.commonEntity.BaseEnum;
import lombok.Getter;

/**
 * @author DavidLoman
 * @create 2025-04-19 9:56
 */
@Getter
public class BizException extends RuntimeException{
    private final int code;

    public BizException(BaseEnum baseEnum) {
        super(baseEnum.getMsg());
        this.code = baseEnum.getCode();
    }


}
