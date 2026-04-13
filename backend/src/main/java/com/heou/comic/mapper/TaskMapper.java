package com.heou.comic.mapper;

import com.heou.comic.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskMapper {
    List<Task> selectList(@Param("taskName") String taskName, @Param("taskType") Integer taskType, @Param("status") Integer status);

    Task selectById(@Param("id") Long id);

    int insert(Task task);

    int update(Task task);

    int deleteById(@Param("id") Long id);

    int countRunning();

    int countByStatus(@Param("status") Integer status);
}
