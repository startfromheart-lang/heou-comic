package com.heou.comic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heou.comic.entity.Comic;
import com.heou.comic.mapper.ComicMapper;
import com.heou.comic.service.ComicService;
import org.springframework.stereotype.Service;

@Service
public class ComicServiceImpl extends ServiceImpl<ComicMapper, Comic> implements ComicService {

    @Override
    public Page<Comic> getComicPage(Integer page, Integer size, String title) {
        Page<Comic> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<Comic> wrapper = new LambdaQueryWrapper<>();
        if (title != null && !title.isEmpty()) {
            wrapper.like(Comic::getTitle, title);
        }
        wrapper.orderByDesc(Comic::getCreateTime);
        return page(pageParam, wrapper);
    }
}
