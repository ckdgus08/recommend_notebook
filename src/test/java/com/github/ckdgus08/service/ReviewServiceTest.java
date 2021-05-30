package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.Review;
import com.github.ckdgus08.domain.enum_.MajorType;
import com.github.ckdgus08.dto.ReviewDto;
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

        ReviewDto reviewDto = ReviewDto.builder().
                userId(userId).
                major(major).
                model(model).
                title(title).
                content(content).build();

        //when
        Review review = reviewService.createReview(reviewDto);

        //then
        Assertions.assertThat(review.getTitle()).isEqualTo(title);
        Assertions.assertThat(review.getMajorType()).isEqualTo(MajorType.valueOf(major));
        Assertions.assertThat(review.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("DB에 등록되어 있지 않은 노트북명 입력시 에러가 발생한다.")
    void validate_model() {
        //given
        String userId = "test_user";
        String major = "테스트";

        String model = "Test Model error";

        String title = "테스트 리뷰 제목";
        String content = "테스트 리뷰 본문";

        ReviewDto reviewDto = ReviewDto.builder().
                userId(userId).
                major(major).
                model(model).
                title(title).
                content(content).build();

        //when
        //then
        assertThatIllegalArgumentException().isThrownBy(
                () -> reviewService.createReview(reviewDto)
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
        Assertions.assertThat(majorType).isEqualTo(result.get(0).getMajorType());
    }

    @Test
    @DisplayName("전공을 입력하지 않으면, 전공에 상관없이 리뷰를 반환한다.")
    void dd() {
        //given
        String model = "";
        PageRequest pageRequest = PageRequest.of(0, 10);

        //when
        List<Review> reviews = reviewService.selectReviewWithMajor(model, null, pageRequest);
        // TODO: 2021/05/30 qureydsl 동적쿼리로 수정하기

        //then
        Assertions.assertThat(reviews.size()).isEqualTo(10);
    }


}
