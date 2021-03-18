package com.github.ckdgus08.domain.enum_;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum MajorGroup {

    // TODO: 2021/03/13 전공, 프로그램 분류하기

    HUMANITY("인문사회계열", Arrays.asList(MajorType.행정학과, MajorType.경제학과)),
    SCIENCE("자연과학계열", Arrays.asList(MajorType.생명과학과, MajorType.산림과학과)),
    ENGINEERING("공학기술계열", Arrays.asList(MajorType.섬유공학과, MajorType.기계공학과)),
    ART("영상예술계열", Arrays.asList(MajorType.산업디자인학과, MajorType.서양화과)),
    COMPUTER("컴퓨터계열", Arrays.asList(MajorType.컴퓨터공학과, MajorType.소프트웨어학과));

    private final String title;
    private final List<MajorType> majorTypes;

    MajorGroup(String title, List<MajorType> majorTypes) {
        this.title = title;
        this.majorTypes = majorTypes;
    }

    public String getTitle() {
        return title;
    }

}
