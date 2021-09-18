package com.github.ckdgus08.controller;

import com.github.ckdgus08.domain.Review;
import com.github.ckdgus08.domain.enum_.MajorType;
import com.github.ckdgus08.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteBookApiControllerTest.class)
public class NoteBookApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @BeforeEach
    private void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(new NotebookApiController(reviewService))
//                .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
//                .alwaysDo(print())
//                .build();
    }

    @Test
    @DisplayName("전공에 맞는 리뷰 select")
    void selectReviewWithMajor() throws Exception {

        String model = "Test notebook1";
        MajorType majorType = MajorType.테스트;
        Pageable pageRequest = PageRequest.of(0, 10);

        Review review = Review.builder().id(500002L)
                .detail("60점 2020.05.21 ㅣ 인터파크 ㅣ do****")
                .title("초기불량 걸렸습니다...")
                .content("불량화소가 있네요. 새로 산 물건에 이런 결함이 있으니 좀 실망스럽네요. 그리고 액정하단부에 뭔 테이프 같은게 묻어있길레 물티슈로 닦으니까 닦이긴 해서 그건 딱히 문제 삼지는 않겠습니다.")
                .build();

        given(reviewService.selectReviewWithMajor(model, majorType, pageRequest))
                .willReturn(Arrays.asList(review));

        //given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("model", "Test notebook1");
        params.add("majorType", "테스트");
        params.add("page", "0");

        //when
        final ResultActions actions = mockMvc.perform(
                get("/api/review")
                        .params(params)
                        .characterEncoding("UTF-8"));

        //then
        actions.andExpect(status().isOk());
    }

}
