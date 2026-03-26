package com.heou.comic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heou.comic.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

    @Select("SELECT COUNT(*) FROM material_project WHERE status = #{status}")
    int countByStatus(@Param("status") Integer status);

    @Select("SELECT COUNT(*) FROM material_project WHERE script_id = #{scriptId}")
    int countProjectsByScriptId(@Param("scriptId") Long scriptId);
}
