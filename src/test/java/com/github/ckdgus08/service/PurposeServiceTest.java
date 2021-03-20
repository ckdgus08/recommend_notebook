package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.Cpu;
import com.github.ckdgus08.domain.Gpu;
import com.github.ckdgus08.domain.Purpose;
import com.github.ckdgus08.domain.enum_.*;
import com.github.ckdgus08.repository.CpuRepository;
import com.github.ckdgus08.repository.GpuRepository;
import com.github.ckdgus08.repository.PurposeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

@SpringBootTest
@Transactional
public class PurposeServiceTest {

    @Autowired
    public EntityManager em;
    @Autowired
    public PurposeService purposeService;
    @Autowired
    public PurposeRepository purposeRepository;
    @Autowired
    public CpuRepository cpuRepository;
    @Autowired
    public GpuRepository gpuRepository;

    @BeforeEach
    void 프로그램_저장() {
        PurposeType[] purposeTypes = PurposeType.values();

        for (PurposeType purposeType : purposeTypes) {
            purposeRepository.save(new Purpose(purposeType));
        }

        Cpu cpu = Cpu.builder()
                .company(CPUType.INTEL)
                .core(4)
                .max_ghz(4.4f)
                .model("i5-1145G7")
                .thread(8)
                .score(10655)
                .build();

        em.persist(cpu);

        Gpu gpu = Gpu.builder()
                .company(GPUType.NVIDIA)
                .display(4)
                .model("GTX 1050")
                .score(5258)
                .vram(4)
                .build();

        em.persist(gpu);
    }

    @Test
    void 프로그램_CPU_최소최저사양_등록() {
        //given
        PurposeType purposeType = PurposeType._테스트;
        Cpu cpu = cpuRepository.findByModel("i5-1145G7").get(0);

        //when
        Cpu registered_cpu = purposeService.add_require_cpu(
                purposeType,
                cpu,
                OS.window,
                SpecLevel.최소사양
        );
        //then
        Assertions.assertThat(cpu.getModel()).isEqualTo(registered_cpu.getModel());

        assertThatIllegalStateException().isThrownBy(
                () -> purposeService.add_require_cpu(
                        purposeType,
                        cpu,
                        OS.window,
                        SpecLevel.최소사양)
        );
    }

    @Test
    void 프로그램_GPU_최소최저사양_등록() {
        //given
        PurposeType purposeType = PurposeType._테스트;
        Gpu gpu = gpuRepository.findByModelAndVram("GTX 1050", 4).get(0);

        //when
        Gpu registered_gpu = purposeService.add_require_gpu(
                purposeType,
                gpu,
                OS.window,
                SpecLevel.최소사양
        );
        //then
        Assertions.assertThat(gpu.getModel()).isEqualTo(registered_gpu.getModel());
        Assertions.assertThat(gpu.getVram()).isEqualTo(registered_gpu.getVram());

        assertThatIllegalStateException().isThrownBy(
                () -> purposeService.add_require_gpu(
                        purposeType,
                        gpu,
                        OS.window,
                        SpecLevel.최소사양)
        );
    }

    @Test
    void 프로그램사양_선택안할시_전공에따라_기본등록() {
        //given

        //when

        //then
    }

    @Test
    void 사용못하는_운영체제_검사() {
        //given

        //when

        //then
    }

    @Test
    void 프로그램선택_최적사양선택() {
        //given

        //when

        //then
    }

    @Test
    void 최소최저사양보기() {
        //given

        //when

        //then
    }


}