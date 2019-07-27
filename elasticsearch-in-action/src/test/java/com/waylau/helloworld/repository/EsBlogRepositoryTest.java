package com.waylau.helloworld.repository;


import com.waylau.helloworld.domain.es.EsBlog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsBlogRepositoryTest {
    @Autowired
    private EsBlogRepository esBlogRepository;

    @Before
    public void initElasticSearchRepository(){
        esBlogRepository.deleteAll();
        esBlogRepository.save(new EsBlog("a","b","c"));
        esBlogRepository.save(new EsBlog("a","b","d"));
        esBlogRepository.save(new EsBlog("a","b","e"));
    }

    @Test
    public void test1() {
        Pageable pageRequest = PageRequest.of(0, 20);
        Page<EsBlog> result = esBlogRepository.
                findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining("a", "b", "a", pageRequest);

        System.out.println("共查到" + result.getTotalElements() + "条数据！");
        List<EsBlog> esBlogList = result.getContent();
        for (EsBlog esBlog : esBlogList) {
            System.out.println(esBlog);

        }

    }


}
