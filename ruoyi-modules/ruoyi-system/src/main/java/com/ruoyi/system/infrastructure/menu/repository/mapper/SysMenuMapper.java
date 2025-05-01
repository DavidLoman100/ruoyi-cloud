package com.ruoyi.system.infrastructure.menu.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.menu.entity.MenuQryEntity;
import com.ruoyi.system.infrastructure.menu.repository.po.SysMenuPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 37504
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
* @createDate 2025-04-22 23:28:57
* @Entity generator.domain.SysMenu
*/
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuPo> {
    List<SysMenuPo> qryMenuByPermission(@Param("qry") MenuQryEntity qryEntity);
}




