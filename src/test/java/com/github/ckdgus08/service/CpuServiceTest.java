package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.Cpu;
import com.github.ckdgus08.domain.enum_.CpuType;
import com.github.ckdgus08.repository.CpuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

@SpringBootTest
@Transactional
public class CpuServiceTest {

    @Autowired
    public EntityManager em;
    @Autowired
    public CpuService cpuService;
    @Autowired
    public CpuRepository cpuRepository;

    @Test
    void 씨피유_저장() {
        //given
        Cpu cpu = Cpu.builder()
                .company(CpuType.INTEL)
                .core(4)
                .maxGhz(4.4f)
                .model("i5-test-01")
                .thread(8)
                .score(10655)
                .build();
        //when
        Long savedId = cpuService.save(cpu);
        List<Cpu> result = cpuRepository.findByModel("i5-test-01");

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(savedId).isEqualTo(result.get(0).getId());
    }

    @Test
    void 씨피유_중복_저장() {
        //given
        Cpu cpu = Cpu.builder()
                .company(CpuType.INTEL)
                .core(4)
                .maxGhz(4.4f)
                .model("i5-test-01")
                .thread(8)
                .score(10655)
                .build();
        //when
        Long savedId1 = cpuService.save(cpu);

        assertThatIllegalStateException().isThrownBy(
                () -> cpuService.save(cpu)
        );
    }

}
