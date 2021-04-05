package com.github.ckdgus08.domain.enum_;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum MajorGroup {

    // TODO: 2021/03/13 전공, 프로그램 분류하기

    HUMANITY("인문사회계열"
            , Arrays.asList(MajorType.행정학과, MajorType.경제학과)
            , Arrays.asList(PurposeType._온라인강의, PurposeType._문서작업)
    ),
    SCIENCE("자연과학계열"
            , Arrays.asList(MajorType.생명과학과, MajorType.산림과학과)
            , Arrays.asList(PurposeType._온라인강의, PurposeType._문서작업)
    ),
    ENGINEERING("공학기술계열",
            Arrays.asList(MajorType.섬유공학과, MajorType.기계공학과)
            , Arrays.asList(PurposeType._온라인강의, PurposeType._문서작업)
    ),
    ART("영상예술계열"
            , Arrays.asList(MajorType.산업디자인학과, MajorType.미술학과)
            , Arrays.asList(PurposeType._온라인강의, PurposeType._문서작업)
    ),
    COMPUTER("컴퓨터계열"
            , Arrays.asList(MajorType.컴퓨터공학과, MajorType.소프트웨어학과)
            , Arrays.asList(PurposeType._온라인강의, PurposeType._문서작업)
    ),
    EMPTY("기타", Arrays.asList(MajorType.기타), Arrays.asList(PurposeType._테스트));

    private final String title;
    private final List<MajorType> majorTypes;
    private final List<PurposeType> purposeTypes;

    MajorGroup(String title, List<MajorType> majorTypes, List<PurposeType> purposeTypes) {
        this.title = title;
        this.majorTypes = majorTypes;
        this.purposeTypes = purposeTypes;
    }

    public static MajorGroup findByPurposeType(MajorType majorType) {
        return Arrays.stream(MajorGroup.values())
                .filter(majorGroup -> majorGroup.hasMajorType(majorType))
                .findAny()
                .orElse(EMPTY);
    }

    public boolean hasMajorType(MajorType majorType) {
        return majorTypes.stream()
                .anyMatch(major -> major == majorType);
    }

}
