package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.Purpose;
import com.github.ckdgus08.domain.enum_.PurposeType;
import com.github.ckdgus08.repository.PurposeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class PurposeServiceTest {

    @Autowired
    public EntityManager em;
    @Autowired
    public PurposeService purposeService;
    @Autowired
    public PurposeRepository purposeRepository;

    @Test
    void 프로그램_저장() {
        //given
        PurposeType[] purposeTypes = PurposeType.values();
        //when

        for (PurposeType purposeType : purposeTypes) {
            purposeRepository.save(new Purpose(purposeType));
        }

        //then
        Assertions.assertThat(purposeTypes.length)
                .isEqualTo(purposeRepository.findAll().size());
    }

    @Test
    void 프로그램선택_최적사양선택() {
        //given

        //when

        //then
    }


}