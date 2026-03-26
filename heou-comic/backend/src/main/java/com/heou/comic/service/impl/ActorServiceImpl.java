package com.heou.comic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heou.comic.entity.Actor;
import com.heou.comic.mapper.ActorMapper;
import com.heou.comic.service.ActorService;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl extends ServiceImpl<ActorMapper, Actor> implements ActorService {

    @Override
    public Page<Actor> getActorPage(Integer page, Integer size, String name) {
        Page<Actor> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Actor> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like(Actor::getName, name);
        }
        wrapper.orderByDesc(Actor::getCreateTime);
        return page(pageParam, wrapper);
    }

    @Override
    public void createActor(Actor actor) {
        save(actor);
    }

    @Override
    public void updateActor(Long id, Actor actor) {
        actor.setId(id);
        updateById(actor);
    }

    @Override
    public void deleteActor(Long id) {
        removeById(id);
    }
}
