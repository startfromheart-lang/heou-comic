package com.heou.comic.service.impl;

import com.heou.comic.entity.Actor;
import com.heou.comic.exception.BusinessException;
import com.heou.comic.mapper.ActorMapper;
import com.heou.comic.mapper.ProjectMapper;
import com.heou.comic.service.ActorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorMapper actorMapper;
    private final ProjectMapper projectMapper;

    public ActorServiceImpl(ActorMapper actorMapper, ProjectMapper projectMapper) {
        this.actorMapper = actorMapper;
        this.projectMapper = projectMapper;
    }

    @Override
    public List<Actor> list(String actorName, Integer status) {
        return actorMapper.selectList(actorName, status);
    }

    @Override
    public Actor getById(Long id) {
        return actorMapper.selectById(id);
    }

    @Override
    public boolean save(Actor actor) {
        return actorMapper.insert(actor) > 0;
    }

    @Override
    public boolean update(Actor actor) {
        return actorMapper.update(actor) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        int projectCount = projectMapper.countByActorId(id);
        if (projectCount > 0) {
            throw new BusinessException("该演员角色已被项目引用，无法删除");
        }
        return actorMapper.deleteById(id) > 0;
    }
}
