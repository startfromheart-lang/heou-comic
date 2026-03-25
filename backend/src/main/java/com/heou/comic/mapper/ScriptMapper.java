package com.heou.comic.mapper;

import com.heou.comic.entity.Script;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScriptMapper {
    List<Script> selectList(@Param("scriptName") String scriptName, @Param("status") Integer status);

    Script selectById(@Param("id") Long id);

    int insert(Script script);

    int update(Script script);

    int deleteById(@Param("id") Long id);
}
