package com.ruoyi.system.infrastructure.user.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.system.domain.user.entity.UserRoleQryEntity;
import com.ruoyi.system.infrastructure.user.repository.po.SysUserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 37504
* @description 针对表【sys_user(用户信息表)】的数据库操作Mapper
* @createDate 2025-03-21 00:14:08
* @Entity generator.domain.SysUserPo
*/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserPo> {

    Page<SysUserPo> pageQryUserRoleByPerms(@Param("page") Page<SysUserPo> page, @Param("qry") UserRoleQryEntity qryEntity);
}




