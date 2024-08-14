package com.kcm.demo.controller;

import com.kcm.demo.entity.Page;
import com.kcm.demo.service.PageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pages")
public class PageController {

    private final PageService pageService;
    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("/{pageNum}")
    public List<Page> selectPages(@PathVariable Long pageNum) {
        return pageService.selectPages(pageNum);
    }

}
