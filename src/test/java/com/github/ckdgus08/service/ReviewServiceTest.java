package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.Review;
import com.github.ckdgus08.domain.enum_.MajorType;
import com.github.ckdgus08.repository.ReviewRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class ReviewServiceTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewService reviewService;


    @Test
    @DisplayName("노트북 모델, 전공선택시 전공에 맞는 리뷰를 반환한다.")
    void selectReviewWithModelAndMajor() {
        //given
        String model = "Test notebook1";
        MajorType majorType = MajorType.테스트;
        Pageable pageRequest = PageRequest.of(0, 10);

        //when
        List<Review> result = reviewService.selectReviewWithMajor(model, majorType, pageRequest);

        //then
        Assertions.assertThat(1).isEqualTo(result.size());
    }

//    @Test
//    @DisplayName("전공을 입력하지 않으면, 전공에 상관없이 리뷰를 반환한다.")
//    void dd() {
//        //given
//        String model = "";
//        MajorType majorType = MajorType.테스트;
//        PageRequest pageRequest = PageRequest.of(0, 10);
//
//        //when
//        reviewService.selectReviewWithMajor(model, majorType, pageRequest);
//
//        //then
//        Assertions.assertThat().isEqualTo();
//    }
//
//    @Test
//    @DisplayName("노트북 모델을 찾지 못하면 에러를 반환한다.")
//    void dd() {
//        //given
//        String model = "";
//        MajorType majorType = MajorType.테스트;
//        PageRequest pageRequest = PageRequest.of(0, 10);
//
//        //when
//        reviewService.selectReviewWithMajor(model, majorType, pageRequest);
//
//        //then
//        Assertions.assertThat().isEqualTo();
//    }


}
