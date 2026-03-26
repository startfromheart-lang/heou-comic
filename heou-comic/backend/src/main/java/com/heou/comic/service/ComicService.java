package com.heou.comic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heou.comic.entity.Comic;

public interface ComicService extends IService<Comic> {

    Page<Comic> getComicPage(Integer page, Integer size, String title);
}
