package com.github.ckdgus08.repository;

import com.github.ckdgus08.domain.Review;
import com.github.ckdgus08.domain.enum_.MajorType;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> selectReviewWithMajor(String model, MajorType majorType, Pageable pageable);

}
