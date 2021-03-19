package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.Cpu;
import com.github.ckdgus08.domain.enum_.CPUType;
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
    public CPUService cpuService;
    @Autowired
    public CpuRepository cpuRepository;

    @Test
    void 씨피유_저장() {
        //given
        Cpu cpu = Cpu.builder()
                .company(CPUType.INTEL)
                .core(4)
                .max_ghz(4.4f)
                .model("i5-1145G7")
                .thread(8)
                .score(10655)
                .build();
        //when
        Long saved_id = cpuService.save(cpu);
        List<Cpu> result = cpuRepository.findByModel("i5-1145G7");

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(saved_id).isEqualTo(result.get(0).getId());
    }

    @Test
    void 씨피유_중복_저장() {
        //given
        Cpu cpu = Cpu.builder()
                .company(CPUType.INTEL)
                .core(4)
                .max_ghz(4.4f)
                .model("i5-1145G7")
                .thread(8)
                .score(10655)
                .build();
        //when
        Long saved_id1 = cpuService.save(cpu);

        assertThatIllegalStateException().isThrownBy(
                () -> cpuService.save(cpu)
        );
    }

    @Test
    void 씨피유_최적사양선택() {
        //given

        //when

        //then
    }

}
