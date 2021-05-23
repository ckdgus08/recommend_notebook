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

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@SpringBootTest
@Transactional
public class ReviewServiceTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewService reviewService;

    @Test
    @DisplayName("후기 등록")
    void createReview() {
        //given
        String userId = "test_user";
        String major = "테스트";

        String model = "Test notebook1";

        String title = "테스트 리뷰 제목";
        String content = "테스트 리뷰 본문";

        //when
        Review review = reviewService.createReview(userId, major, model, title, content);

        //then
        Assertions.assertThat(review.getTitle()).isEqualTo(title);
        Assertions.assertThat(review.getMajorType()).isEqualTo(MajorType.valueOf(major));
        Assertions.assertThat(review.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("후기 등록시 DB에 등록되어 있지 않은 노트북명 입력시 에러가 발생한다.")
    void validate_model() {
        //given
        String userId = "test_user";
        String major = "테스트";

        String model = "없는 노트북 모델명";

        String title = "테스트 리뷰 제목";
        String content = "테스트 리뷰 본문";

        //when
        //then
        assertThatIllegalArgumentException().isThrownBy(
                () -> reviewService.createReview(userId, major, model, title, content)
        );
    }

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
