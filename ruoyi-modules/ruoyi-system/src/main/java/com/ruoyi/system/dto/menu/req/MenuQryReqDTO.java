package com.ruoyi.system.dto.menu.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author DavidLoman
 * @create 2025-04-30 17:37
 */
@Data
public class MenuQryReqDTO{
    @Schema(description = "菜单名称")
    private String menuName;

    @Schema(description = "菜单状态（0正常 1停用）")
    private String status;
}


