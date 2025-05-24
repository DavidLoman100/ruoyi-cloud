package com.ruoyi.system.infrastructure.role.repository.mapper;

import com.ruoyi.system.infrastructure.role.repository.po.SysRoleMenuPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 37504
* @description 针对表【sys_role_menu(角色和菜单关联表)】的数据库操作Mapper
* @createDate 2025-04-26 17:22:51
* @Entity generator.domain.SysRoleMenu
*/
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuPo> {

    int insertBatch(@Param("list") List<SysRoleMenuPo> sysRoleMenuPos);
}




