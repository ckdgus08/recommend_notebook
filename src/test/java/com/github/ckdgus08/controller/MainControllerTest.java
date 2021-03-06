package com.github.ckdgus08.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class MainControllerTest {

    @Autowired
    public EntityManager em;
    @Autowired
    public MainController mainController;

//    @Test
//    @DisplayName("전공은 필수적으로 선택되어야 한다.")
//    void 전공_필수선택() {
//        //given
//        SearchCondition searchCondition = SearchCondition.builder()
//                .major(null)
//                .build();
//        //when
//
//        //then
//        assertThatIllegalArgumentException().isThrownBy(
//                () -> mainController.searchNotebook(searchCondition)
//        );
//    }
//
//    @DisplayName("전공 입력은 MajorType에 등록된 값만 가능하다.")
//    @Test
//    void 전공_입력값() {
//        //given
//        String majorString = "majorTest";
//
//        //when
//
//        //then
//        assertThatIllegalArgumentException().isThrownBy(
//                () -> MajorType.valueOf(majorString)
//        );
//    }
//
//    @DisplayName("전공 입력은 한글로 구성된 하나의 String 값이여야 한다.")
//    @Test
//    void 전공_입력_유효성() {
//        //given
//        String successMajor1 = "기계공학과";
//        String successMajor2 = "abcde";
//        String failMajor1 = "_기계";
//        //when
//        boolean successResult1 = mainController.validateMajor(trueMajor1);
//        boolean successResult2 = mainController.validateMajor(trueMajor2);
//        boolean failResult1 = mainController.validateMajor(falseMajor1);
//        //then
//        assertThat(successResult1).isEqualTo(true);
//        assertThat(successResult2).isEqualTo(true);
//        assertThat(failResult1).isEqualTo(true);
//    }

    @DisplayName("프로그램은 필수적으로 선택되지 않으면 전공에 따라 자동으로 선택된다.")
    @Test
    void 프로그램_자동설정() {
        //given

        //when

        //then
    }

    @DisplayName("프로그램 하나 이상 선택시 선택된 프로그램으로 검색된다.")
    @Test
    void 프로그램_선택시() {
        //given

        //when

        //then
    }

    @DisplayName("프로그램은 PurposeType에 등록된 값만 가능하다.")
    @Test
    void 프로그램_입력값() {
        //given

        //when

        //then
    }

    @DisplayName("프로그램 입력값은 ,로 구분된 한글,숫자,영어만 가능하다.")
    @Test
    void 프로그램_입력유효성() {
        //given

        //when

        //then
    }

    @DisplayName("가격이 선택되지 않으면 모든 가격 범위로 설정된다.")
    @Test
    void 가격_자동설정() {
        //given

        //when

        //then
    }

    @DisplayName("가격이 선택되면 선택된 범위로 검색된다.")
    @Test
    void 가격_선택() {
        //given

        //when

        //then
    }

    @DisplayName("가격 입력은 최소,최대의 형식의 숫자로 입력되어야 한다.")
    @Test
    void 가격_입력유효성() {
        //given

        //when

        //then
    }

    @DisplayName("무게가 선택되지 않으면 모든 무게 범위로 설정된다.")
    @Test
    void 무게_자동설정() {
        //given

        //when

        //then
    }

    @DisplayName("무게가 선택되면 선택된 범위로 검색된다.")
    @Test
    void 무게_선택() {
        //given

        //when

        //then
    }

    @DisplayName("무게 입력은 최소,최대의 형식으로 입력되어야 한다.")
    @Test
    void 무게_입력유효성() {
        //given

        //when

        //then
    }

    @DisplayName("화면크기가 선택되지 않으면 모든 화면크기 범위로 설정된다.")
    @Test
    void 화면크기_자동설정() {
        //given

        //when

        //then
    }

    @DisplayName("화면크기가 선택되면 선택된 범위로 검색된다.")
    @Test
    void 화면크기_선택() {
        //given

        //when

        //then
    }

    @DisplayName("화면크기 입력은 최소,최대의 형식으로 입력되어야 한다.")
    @Test
    void 화면크기_입력유효성() {
        //given

        //when

        //then
    }

    @DisplayName("우선순위 선택에 따라 검색시 정렬순서가 다르다.")
    @Test
    void 우선순위_() {
        //given

        //when

        //then
    }


}
