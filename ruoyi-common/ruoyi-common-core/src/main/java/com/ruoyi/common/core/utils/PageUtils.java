package com.ruoyi.common.core.utils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.utils.sql.SqlUtil;
import com.ruoyi.common.core.web.page.PageDomain;
import com.ruoyi.common.core.web.page.TableSupport;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具类
 *
 * @author ruoyi
 */
public class PageUtils {
    /**
     * 设置请求分页数据
     */
    public static Page<T> startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        //String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());

        // 创建 MyBatis Plus 分页对象
        Page<T> page = new Page<>(pageNum, pageSize);
        // 设置排序
        /*
        if (StringUtils.isNotBlank(orderBy)) {
            List<OrderItem> orderItems = new ArrayList<>();
            for (String order : orderBy.split(",")) {
                String[] parts = order.trim().split("\\s+");
                if (parts.length == 2) {
                    String column = parts[0];
                    boolean asc = "asc".equalsIgnoreCase(parts[1]);
                    orderItems.add(asc ? OrderItem.asc(column) : OrderItem.desc(column));
                }
            }
            page.addOrder(orderItems);
        }
         */
        return page;
    }

}
