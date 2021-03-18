package com.github.ckdgus08.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class PurposeRepositoryImpl implements PurposeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PurposeRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

}
