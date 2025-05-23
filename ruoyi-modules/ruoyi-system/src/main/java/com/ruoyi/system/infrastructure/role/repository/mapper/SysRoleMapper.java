package com.ruoyi.system.infrastructure.role.repository.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.role.entity.RolePageQryEntity;
import com.ruoyi.system.infrastructure.role.repository.po.SysRolePo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 37504
* @description 针对表【sys_role(角色信息表)】的数据库操作Mapper
* @createDate 2025-04-26 17:06:42
* @Entity generator.domain.SysRole
*/
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRolePo> {

    Page<SysRolePo> pageQryRoleListByPerms(@Param("page") Page<SysRolePo> rolePoPage, @Param("qry") RolePageQryEntity entity);


    SysRolePo getRoleByPerms(@Param("roleId") Long roleId, @Param("dataScopeSql") String dataScopeSql);
}




