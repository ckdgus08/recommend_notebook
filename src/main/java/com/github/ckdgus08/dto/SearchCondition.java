package com.github.ckdgus08.dto;

import com.github.ckdgus08.domain.enum_.MajorType;
import com.github.ckdgus08.domain.enum_.PurposeType;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCondition {

    private MajorType major;
    private List<PurposeType> purposeTypes;

    private int minPrice;
    private int maxPrice;

    private float minWeight;
    private float maxWeight;

    private float minInch;
    private float maxInch;

    private Map<Integer, String> searchOrder = new HashMap<>();

    public SearchCondition(String major, String purposeString, String priceString, String weightString, String inchString) {

        // TODO: 2021/03/30 생성자 코드 완성하기
        this.major = MajorType.valueOf(major);
    }


}
