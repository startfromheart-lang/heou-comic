package com.heou.comic.service;

import com.heou.comic.entity.Comic;

import java.util.List;

public interface ComicService {
    List<Comic> list(String comicName, Integer status);

    Comic getById(Long id);

    boolean save(Comic comic);

    boolean update(Comic comic);

    boolean deleteById(Long id);
}
