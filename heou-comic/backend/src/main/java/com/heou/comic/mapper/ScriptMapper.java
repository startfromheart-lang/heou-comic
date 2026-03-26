package com.heou.comic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heou.comic.entity.Script;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ScriptMapper extends BaseMapper<Script> {

    @Select("SELECT COUNT(*) FROM material_project WHERE script_id = #{scriptId}")
    int countProjectsByScriptId(@Param("scriptId") Long scriptId);
}
