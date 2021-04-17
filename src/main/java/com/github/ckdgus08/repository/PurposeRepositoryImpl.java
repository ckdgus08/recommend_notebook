package com.github.ckdgus08.repository;

import com.github.ckdgus08.domain.Purpose;
import com.github.ckdgus08.domain.enum_.PurposeType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.github.ckdgus08.domain.QPurpose.purpose;
import static java.util.Collections.EMPTY_LIST;

public class PurposeRepositoryImpl implements PurposeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PurposeRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Purpose> findByPurposeTypeList(List<PurposeType> purposeTypeList) {
        if (purposeTypeList.size() == 0) return EMPTY_LIST;
        return queryFactory
                .selectFrom(purpose)
                .where(
                        purposeListEqual(purposeTypeList)
                ).fetch();
    }

    private Predicate purposeListEqual(List<PurposeType> purposeCond) {
        BooleanBuilder result = new BooleanBuilder();
        for (PurposeType s : purposeCond) {
            result.or(purpose.purposeType.eq(s));
        }
        return result;
    }

}
