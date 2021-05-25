package com.github.ckdgus08.apiController;

import com.github.ckdgus08.domain.Review;
import com.github.ckdgus08.domain.enum_.MajorType;
import com.github.ckdgus08.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewApiController {

    private final ReviewService reviewService;

    @GetMapping("/v1/api/review")
    public List<Review> selectReviewWithMajor(
            @RequestParam(value = "model") String model,
            @RequestParam(value = "majorType") MajorType majorType,
            @RequestParam(value = "page", defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, 10);

        return reviewService.selectReviewWithMajor(model, majorType, pageable);
    }

    @PostMapping("/v1/api/review")
    public void createReview(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "major") String major,
            @RequestParam(value = "model") String model,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content
    ) {
        reviewService.createReview(userId, major, model, title, content);
    }

    @PutMapping("/v1/api/review")
    public void editReview(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "major") String major,
            @RequestParam(value = "model") String model,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content
    ) {
        // TODO: 2021/05/25 리뷰 수정 권한체크
        reviewService.createReview(userId, major, model, title, content);
    }

    @DeleteMapping("/v1/api/review")
    public void deleteReview(
            @RequestParam(value = "review_id") Long review_id,
            @RequestParam(value = "user_id") String user_id
    ) {
        // TODO: 2021/05/25 리뷰 삭제 권한체크
        reviewService.deleteReview(review_id, user_id);
    }
}
