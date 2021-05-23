package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.Review;
import com.github.ckdgus08.domain.enum_.MajorType;
import com.github.ckdgus08.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> selectReviewWithMajor(String model, MajorType majorType, Pageable pageRequest) {
        return reviewRepository.selectReviewWithMajor(model, majorType, pageRequest);
    }

    public Review createReview(String userId, String major, String model, String title, String content) {

        // TODO: 2021/05/23 user 엔티티 추가
//        Review review = new Review()
//        reviewRepository.save();
        return null;
    }
}
