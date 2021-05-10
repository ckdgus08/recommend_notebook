package com.github.ckdgus08.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.ckdgus08.domain.enum_.ReviewType;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class ReviewDeep extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewDeepId")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReviewType reviewType;

    @Column(length = 1024)
    private String content;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewId")
    private Review review;

    public ReviewDeep(Review review, ReviewType reviewType, String content) {
        this.id = null;
        this.reviewType = reviewType;
        this.content = content;
        if (review != null)
            addReviewDeep(review);
    }

    public void addReviewDeep(Review review) {
        this.review = review;
        review.getReviewDeeps().add(this);
    }
}
