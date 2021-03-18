package com.github.ckdgus08.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class NotebookRepositoryImpl implements NotebookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public NotebookRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

}
