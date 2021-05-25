package com.github.ckdgus08.controller;

import com.github.ckdgus08.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    public ReviewService reviewService;

    @GetMapping("/review")
    public String review() {
        return "review";
    }

}
