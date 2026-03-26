package com.heou.comic.service.impl;

import com.heou.comic.entity.Comic;
import com.heou.comic.mapper.ComicMapper;
import com.heou.comic.service.ComicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicServiceImpl implements ComicService {

    private final ComicMapper comicMapper;

    public ComicServiceImpl(ComicMapper comicMapper) {
        this.comicMapper = comicMapper;
    }

    @Override
    public List<Comic> list(String comicName, Integer status) {
        return comicMapper.selectList(comicName, status);
    }

    @Override
    public Comic getById(Long id) {
        return comicMapper.selectById(id);
    }

    @Override
    public boolean save(Comic comic) {
        return comicMapper.insert(comic) > 0;
    }

    @Override
    public boolean update(Comic comic) {
        return comicMapper.update(comic) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return comicMapper.deleteById(id) > 0;
    }
}
