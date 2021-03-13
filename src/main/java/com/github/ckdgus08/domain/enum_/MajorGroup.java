package com.github.ckdgus08.domain.enum_;

import java.util.Arrays;
import java.util.List;

public enum MajorGroup {

    // TODO: 2021/03/13 전공, 프로그램 분류하기

    공통("공통", Arrays.asList(PurposeType._온라인강의)),
    기계("기계", Arrays.asList(PurposeType._오토캐드, PurposeType._솔리드웍스)),
    인문("인문", Arrays.asList(PurposeType._R프로그래밍));

    private String title;
    private List<PurposeType> purposeList;

    MajorGroup(String title, List<PurposeType> purposeList) {
        this.title = title;
        this.purposeList = purposeList;
    }

    public String getTitle() {
        return title;
    }

}
