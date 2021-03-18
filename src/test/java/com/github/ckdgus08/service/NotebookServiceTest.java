package com.github.ckdgus08.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
public class NotebookServiceTest {

    @Autowired
    public EntityManager em;

    @Autowired
    public NotebookService notebookService;

}
