package com.github.ckdgus08.domain.enum_;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PurposeGroup {

    공부("공부", Arrays.asList(PurposeType._온라인강의)),
    공대프로그램("공대프로그램", Arrays.asList(PurposeType._온라인강의)),

    EMPTY("없음", Collections.emptyList());

    private String title;
    private List<PurposeType> purposeList;

    PurposeGroup(String title, List<PurposeType> purposeList) {
        this.title = title;
        this.purposeList = purposeList;
    }

    public static PurposeGroup findByPurposeType(PurposeType purposeType) {
        return Arrays.stream(PurposeGroup.values())
                .filter(purposeGroup -> purposeGroup.hasPurposeType(purposeType))
                .findAny()
                .orElse(EMPTY);
    }

    public boolean hasPurposeType(PurposeType purposeType) {
        return purposeList.stream()
                .anyMatch(purpose -> purpose == purposeType);
    }

    public String getTitle() {
        return title;
    }
}
