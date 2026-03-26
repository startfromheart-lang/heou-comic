package com.heou.comic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heou.comic.entity.Actor;

public interface ActorService extends IService<Actor> {

    Page<Actor> getActorPage(Integer page, Integer size, String name);

    void createActor(Actor actor);

    void updateActor(Long id, Actor actor);

    void deleteActor(Long id);
}
