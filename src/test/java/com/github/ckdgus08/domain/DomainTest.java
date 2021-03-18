package com.github.ckdgus08.domain;

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

    JPAQueryFactory queryFactory;

    @BeforeEach
    void query_inject() {
//        Purpose purpose1 = new Purpose("_테스트1");
//        Purpose purpose2 = new Purpose("_테스트2");
//        Purpose purpose3 = new Purpose("_테스트3");
//
//        em.persist(purpose1);
//        em.persist(purpose2);
//        em.persist(purpose3);
//
//        Cpu cpu1 = new Cpu("인텔");
//        cpu1.setName("테스트1");
//        cpu1.setScore(8000);
//        cpu1.setOrigin_ghz(1.0f);
//        cpu1.setMax_ghz(2.0f);
//        cpu1.setCore(4);
//
//        em.persist(cpu1);
//
//        PurposeCpu purposeCpu1 = new PurposeCpu(purpose1, cpu1, OS.window, SpecLevel.최소사양);
//        em.persist(purposeCpu1);
//
//        PurposeOs purposeOs1 = new PurposeOs(purpose1, OS.window);
//        em.persist(purposeOs1);
//
//        Cpu cpu2 = new Cpu("AMD");
//        cpu2.setName("테스트2");
//        cpu2.setScore(8000);
//        cpu2.setOrigin_ghz(1.6f);
//        cpu2.setMax_ghz(2.0f);
//        cpu2.setCore(4);
//
//        em.persist(cpu2);
//
//        PurposeCpu purposeCpu2 = new PurposeCpu(purpose2, cpu2, OS.window, SpecLevel.최소사양);
//        em.persist(purposeCpu2);
//
//        PurposeOs purposeOs2 = new PurposeOs(purpose2, OS.window);
//        em.persist(purposeOs2);
//
//        Cpu cpu3 = new Cpu("인텔");
//        cpu3.setName("테스트3");
//        cpu3.setScore(10000);
//        cpu3.setOrigin_ghz(0.8f);
//        cpu3.setMax_ghz(2.1f);
//        cpu3.setCore(6);
//
//        em.persist(cpu3);
//
//        PurposeCpu purposeCpu3 = new PurposeCpu(purpose3, cpu3, OS.window, SpecLevel.최소사양);
//        em.persist(purposeCpu3);
//
//        PurposeOs purposeOs3 = new PurposeOs(purpose1, OS.window);
//        em.persist(purposeOs3);
//
//        Cpu cpu4 = new Cpu("인텔");
//        cpu4.setName("테스트4");
//        cpu4.setScore(10001);
//        cpu4.setOrigin_ghz(2.3f);
//        cpu4.setMax_ghz(2.7f);
//        cpu4.setCore(4);
//
//        em.persist(cpu4);
//
//        Cpu cpu5 = new Cpu("AMD");
//        cpu5.setName("테스트5");
//        cpu5.setScore(11);
//        cpu5.setOrigin_ghz(3f);
//        cpu5.setMax_ghz(3f);
//        cpu5.setCore(8);
//
//        em.persist(cpu5);
//
//        Notebook notebook1 = new Notebook(Company.TEST);
//        notebook1.setModel("test1");
//        notebook1.setCpu(cpu3);
//        notebook1.setInch(14f);
//        notebook1.setPrice(1);
//        notebook1.setWeight(2.1f);
//
//        em.persist(notebook1);
//
//        Notebook notebook2 = new Notebook(Company.TEST);
//        notebook2.setModel("test2");
//        notebook2.setCpu(cpu4);
//        notebook2.setInch(14f);
//        notebook2.setPrice(2);
//        notebook2.setWeight(2.1f);
//        em.persist(notebook2);
//
//        Notebook notebook3 = new Notebook(Company.TEST);
//        notebook3.setModel("test3");
//        notebook3.setCpu(cpu5);
//        notebook3.setInch(14f);
//        notebook3.setPrice(3);
//        notebook3.setWeight(2.1f);
//        em.persist(notebook3);
//
//        NotebookPurpose notebookPurpose1 = new NotebookPurpose(notebook1, purpose1);
//        NotebookPurpose notebookPurpose2 = new NotebookPurpose(notebook1, purpose2);
//        NotebookPurpose notebookPurpose3 = new NotebookPurpose(notebook1, purpose3);
//        NotebookPurpose notebookPurpose4 = new NotebookPurpose(notebook2, purpose1);
//        NotebookPurpose notebookPurpose5 = new NotebookPurpose(notebook2, purpose2);
//        NotebookPurpose notebookPurpose6 = new NotebookPurpose(notebook2, purpose3);
//        NotebookPurpose notebookPurpose7 = new NotebookPurpose(notebook3, purpose1);
//        NotebookPurpose notebookPurpose8 = new NotebookPurpose(notebook3, purpose2);
//        NotebookPurpose notebookPurpose9 = new NotebookPurpose(notebook3, purpose3);
//
//        em.persist(notebookPurpose1);
//        em.persist(notebookPurpose2);
//        em.persist(notebookPurpose3);
//        em.persist(notebookPurpose4);
//        em.persist(notebookPurpose5);
//        em.persist(notebookPurpose6);
//        em.persist(notebookPurpose7);
//        em.persist(notebookPurpose8);
//        em.persist(notebookPurpose9);
    }

    @Test
    void 테스트() {
        //given

        //when

        //then
    }


}
