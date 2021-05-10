package com.github.ckdgus08.domain;

import com.github.ckdgus08.domain.enum_.Company;
import com.github.ckdgus08.domain.enum_.Os;
import com.github.ckdgus08.repository.NotebookRepository;
import com.github.ckdgus08.service.NotebookService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@SpringBootTest
@Transactional
public class DomainTest {

    @Autowired
    EntityManager em;
    @Autowired
    NotebookService notebookService;

    @Autowired
    NotebookRepository notebookRepository;

    JPAQueryFactory queryFactory;


    @BeforeEach
    void queryInject() {

        Notebook.builder()
                .model("삼성전자 노트북5 metal NT550XAA-K28T")
                .company(Company.삼성전자)
                .inch(15.6f)
                .os(Os.none)
                .price(816630)
                .ram(8)
                .hdd(1024)
                .ssd(0)
                .weight(1.95f);
        Notebook.builder()
                .model("LG전자 15G870-PA50K")
                .company(Company.LG전자)
                .inch(15.6f)
                .os(Os.none)
                .price(1291990)
                .ram(8)
                .hdd(0)
                .ssd(256)
                .weight(2.5f);
    }

    @Test
    void test() {
        //given
        //when

        //then
    }


}
