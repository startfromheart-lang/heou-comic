package com.heou.comic.service;

import com.heou.comic.entity.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> list(String actorName, Integer status);

    Actor getById(Long id);

    boolean save(Actor actor);

    boolean update(Actor actor);

    boolean deleteById(Long id);
}
