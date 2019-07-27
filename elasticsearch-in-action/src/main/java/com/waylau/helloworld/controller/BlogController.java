package com.waylau.helloworld.controller;


import com.waylau.helloworld.domain.es.EsBlog;
import com.waylau.helloworld.repository.EsBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private EsBlogRepository esBlogRepository;

    @GetMapping
    public List<EsBlog> getBlogs(@RequestParam(name = "title") String title,
                                 @RequestParam(name = "summary") String summary,
                                 @RequestParam(name = "content") String content,
                                 @RequestParam(name = "pageIndex",defaultValue = "0") Integer pageIndex,
                                 @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize) {

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<EsBlog> esBlogList = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(title, summary, content, pageable);

        return esBlogList.getContent();
    }
}
