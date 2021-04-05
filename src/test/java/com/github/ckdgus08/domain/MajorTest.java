package com.github.ckdgus08.domain;

import com.github.ckdgus08.domain.enum_.MajorGroup;
import com.github.ckdgus08.domain.enum_.MajorType;
import com.github.ckdgus08.domain.enum_.PurposeType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MajorTest {

    @Test
    void 프로그램사양_선택안할시_전공에따라_기본등록() {
        //given
        MajorType majorType = MajorType.기타;

        //when
        MajorGroup majorGroup = MajorGroup.findByPurposeType(majorType);

        List<PurposeType> result = Stream.concat(majorType.getPurposeTypes().stream(), majorGroup.getPurposeTypes().stream())
                .distinct().collect(Collectors.toList());

        //then
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0)).isEqualTo(PurposeType._테스트);
    }

}
