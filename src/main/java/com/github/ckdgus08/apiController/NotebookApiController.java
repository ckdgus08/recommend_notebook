package com.github.ckdgus08.apiController;

import com.github.ckdgus08.domain.Review;
import com.github.ckdgus08.domain.enum_.MajorType;
import com.github.ckdgus08.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotebookApiController {

    private final ReviewService reviewService;

    @GetMapping("/api/review")
    public List<Review> findAllBbs(
            @RequestParam(value = "model") String model,
            @RequestParam(value = "majorType") MajorType majorType,
            @RequestParam(value = "page", defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, 10);

        return reviewService.selectReviewWithMajor(model, majorType, pageable);
    }

}
