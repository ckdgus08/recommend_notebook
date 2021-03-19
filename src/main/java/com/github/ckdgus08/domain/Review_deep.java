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
public class Review_deep extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_deep_id")
    private Long id;

    private ReviewType reviewType;

    @Column(length = 1024)
    private String content;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    public Review_deep(Review review, ReviewType reviewType, String content) {
        this.id = null;
        this.reviewType = reviewType;
        this.content = content;
        if (review != null)
            addReview_deep(review);
    }

    public void addReview_deep(Review review) {
        this.review = review;
        review.getReview_deeps().add(this);
    }
}
