package com.kcm.demo.service;

import com.kcm.demo.entity.Event;
import com.kcm.demo.entity.Page;
import com.kcm.demo.repository.PageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {

    private final PageRepository pageRepository;
    public PageService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }


    public List<Page> selectPages(Long pageNum) {
        return pageRepository.findByPageNum(pageNum);
    }
}
