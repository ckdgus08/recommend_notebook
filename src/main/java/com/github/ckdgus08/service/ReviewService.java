package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.Review;
import com.github.ckdgus08.domain.enum_.MajorType;
import com.github.ckdgus08.repository.ReviewRepository;
import com.github.ckdgus08.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final NotebookService notebookService;

    public List<Review> selectReviewWithMajor(String model, MajorType majorType, Pageable pageRequest) {
        return reviewRepository.selectReviewWithMajor(model, majorType, pageRequest);
    }

    @Transactional
    public Review createReview(String userId, String major, String model, String title, String content) {

        Review review = Review.builder()
                .user(userRepository.findByUserId(userId))
                .majorType(MajorType.valueOf(major))
                .notebook(notebookService.findByModel(model))
                .title(title)
                .content(content)
                .build();

        return reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Long reviewId, String userId) {

    }


    @Transactional
    public void editReview(Long reviewId, String userId) {

    }


}
