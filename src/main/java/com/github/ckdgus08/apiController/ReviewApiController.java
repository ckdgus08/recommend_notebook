package com.github.ckdgus08.apiController;

import com.github.ckdgus08.domain.Review;
import com.github.ckdgus08.domain.enum_.MajorType;
import com.github.ckdgus08.dto.ReviewDto;
import com.github.ckdgus08.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReviewApiController {

    private final ReviewService reviewService;

    @GetMapping("/v1/api/review")
    public List<Review> selectReviewWithMajor(
            @RequestParam(value = "model") String model,
            @RequestParam(value = "majorType", defaultValue = "기타") MajorType majorType,
            @RequestParam(value = "page", defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, 10);

        List<Review> reviews = reviewService.selectReviewWithMajor(model, majorType, pageable);
        List<Review> majorReview = reviews.stream()
                .filter(it -> it.getMajorType() == majorType)
                .collect(Collectors.toList());

        List<Review> sorted = reviews.stream()
                .filter(it -> it.getContent().length() <= 250)
                .sorted(
                        (o1, o2) -> o2.getContent().length() - o1.getContent().length()
                )
                .limit(5)
                .collect(Collectors.toList());

        if (majorReview.size() >= 1) {
            return majorReview;
        } else {
            return sorted;
        }
    }

    @PostMapping("/v1/api/review")
    public void createReview(
            @RequestBody ReviewDto reviewDto
    ) {
        reviewService.createReview(reviewDto);
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
