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

    private int min_price;
    private int max_price;

    private float min_weight;
    private float max_weight;

    private float min_inch;
    private float max_inch;

    private Map<Integer, String> search_order = new HashMap<>();

    public SearchCondition(String major, String purpose_string, String price_string, String weight_string, String inch_string) {

        // TODO: 2021/03/30 생성자 코드 완성하기
        this.major = MajorType.valueOf(major);
    }


}
