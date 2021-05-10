package com.github.ckdgus08.repository;

import com.github.ckdgus08.domain.Review;
import com.github.ckdgus08.domain.enum_.MajorType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.github.ckdgus08.domain.QNotebook.notebook;
import static com.github.ckdgus08.domain.QReview.review;

public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Review> selectReviewWithMajor(String model, MajorType majorType, Pageable pageable) {
        return queryFactory.selectFrom(review)
                .join(review.notebook, notebook).fetchJoin()
                .where(
                        notebook.model.eq(model),
                        review.majorType.eq(majorType))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
    }
}
