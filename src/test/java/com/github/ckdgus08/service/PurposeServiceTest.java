package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.Cpu;
import com.github.ckdgus08.domain.Gpu;
import com.github.ckdgus08.domain.enum_.*;
import com.github.ckdgus08.dto.ScoreCondition;
import com.github.ckdgus08.repository.CpuRepository;
import com.github.ckdgus08.repository.GpuRepository;
import com.github.ckdgus08.repository.PurposeRepository;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
        Cpu cpu1 = Cpu.builder()
                .company(CpuType.INTEL)
                .core(4)
                .max_ghz(4.4f)
                .model("i5-1145G7")
                .thread(8)
                .score(10655)
                .build();
        Cpu cpu2 = Cpu.builder()
                .company(CpuType.INTEL)
                .core(10)
                .max_ghz(3.7f)
                .model("i9-10900X")
                .thread(20)
                .score(22742)
                .build();
        Cpu cpu3 = Cpu.builder()
                .company(CpuType.AMD)
                .core(8)
                .max_ghz(4.6f)
                .model("Ryzen 9 5900HS")
                .thread(16)
                .score(23165)
                .build();

        em.persist(cpu1);
        em.persist(cpu2);
        em.persist(cpu3);

        Gpu gpu1 = Gpu.builder()
                .company(GpuType.NVIDIA)
                .display(4)
                .model("GTX 1050")
                .score(5258)
                .vram(4)
                .build();
        Gpu gpu2 = Gpu.builder()
                .company(GpuType.AMD)
                .display(4)
                .model("Radeon RX 580")
                .score(8803)
                .vram(8)
                .build();

        em.persist(gpu1);
        em.persist(gpu2);
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
                Os.window,
                SpecLevel.최소사양
        );
        //then
        assertThat(cpu.getModel()).isEqualTo(registered_cpu.getModel());

        assertThatIllegalStateException().isThrownBy(
                () -> purposeService.add_require_cpu(
                        purposeType,
                        cpu,
                        Os.window,
                        SpecLevel.최소사양)
        );
    }

    @Test
    void 프로그램_GPU_최소최저사양_등록() {
        //given
        PurposeType purposeType = PurposeType._테스트;
        Gpu gpu1 = gpuRepository.findByModelAndVram("GTX 1050", 4).get(0);
        Gpu gpu2 = gpuRepository.findByModelAndVram("Radeon RX 580", 8).get(0);

        //when
        Gpu registered_gpu1 = purposeService.add_require_gpu(
                purposeType,
                gpu1,
                Os.window,
                SpecLevel.최소사양
        );
        Gpu registered_gpu2 = purposeService.add_require_gpu(
                purposeType,
                gpu2,
                Os.window,
                SpecLevel.최소사양
        );
        //then
        assertThat(gpu1.getModel()).isEqualTo(registered_gpu1.getModel());
        assertThat(gpu1.getVram()).isEqualTo(registered_gpu1.getVram());
        assertThat(gpu2.getModel()).isEqualTo(registered_gpu2.getModel());
        assertThat(gpu2.getVram()).isEqualTo(registered_gpu2.getVram());

        assertThat(2).isEqualTo(
                purposeRepository.findByPurposeType(purposeType)
                        .get().getPurposeGpus().size()
        );

        assertThatIllegalStateException().isThrownBy(
                () -> purposeService.add_require_gpu(
                        purposeType,
                        gpu1,
                        Os.window,
                        SpecLevel.최소사양)
        );
    }

    @Test
    void 프로그램_RAM_최소최저사양_등록() {
        //given
        PurposeType purposeType = PurposeType._테스트;
        Integer ram = 4;

        //when
        Integer registered_ram = purposeService.add_require_ram(
                purposeType,
                ram,
                Os.window,
                SpecLevel.최소사양
        );
        //then
        assertThat(ram).isEqualTo(registered_ram);

        assertThatIllegalStateException().isThrownBy(
                () -> purposeService.add_require_ram(
                        purposeType,
                        ram,
                        Os.window,
                        SpecLevel.최소사양)
        );
    }

    @Test
    void 여러프로그램_최적CPU_선택() {
        //given
        PurposeType[] purposeType = Arrays.array(PurposeType._테스트, PurposeType._2D캐드);

        Cpu cpu1 = cpuRepository.findByModel("i5-1145G7").get(0);
        Cpu cpu2 = cpuRepository.findByModel("i9-10900X").get(0);
        Cpu cpu3 = cpuRepository.findByModel("Ryzen 9 5900HS").get(0);

        //when
        purposeService.add_require_cpu(
                purposeType[0],
                cpu1,
                Os.window,
                SpecLevel.최소사양
        );
        purposeService.add_require_cpu(
                purposeType[0],
                cpu2,
                Os.window,
                SpecLevel.최소사양
        );
        purposeService.add_require_cpu(
                purposeType[1],
                cpu1,
                Os.window,
                SpecLevel.최소사양
        );
        purposeService.add_require_cpu(
                purposeType[1],
                cpu3,
                Os.window,
                SpecLevel.최소사양
        );

        //when
        Map<CpuType, Optional<Integer>> map = purposeService.select_cpu_from_purposeType_array(
                purposeType, Os.window, SpecLevel.최소사양);

        assertThat(22742).isEqualTo(map.get(CpuType.INTEL).stream().max(Integer::compare).get());
        assertThat(23165).isEqualTo(map.get(CpuType.AMD).stream().max(Integer::compare).get());


        //then
    }

    @Test
    void 여러프로그램_최적GPU_선택() {
        PurposeType[] purposeType = Arrays.array(PurposeType._테스트, PurposeType._2D캐드);

        Gpu gpu1 = gpuRepository.findByModelAndVram("GTX 1050", 4).get(0);
        Gpu gpu2 = gpuRepository.findByModelAndVram("Radeon RX 580", 8).get(0);

        //when
        purposeService.add_require_gpu(
                purposeType[0],
                gpu1,
                Os.window,
                SpecLevel.최소사양
        );
        purposeService.add_require_gpu(
                purposeType[1],
                gpu2,
                Os.window,
                SpecLevel.최소사양
        );

        //when
        Map<GpuType, Optional<Integer>> map = purposeService.select_gpu_from_purposeType_array(
                purposeType, Os.window, SpecLevel.최소사양);

        assertThat(5258).isEqualTo(map.get(GpuType.NVIDIA).get());
        assertThat(8803).isEqualTo(map.get(GpuType.AMD).get());
    }

    @Test
    void 프로그램선택_최적사양선택() {

        // TODO: 2021/03/25 함수 하나로 합치기

        PurposeType[] purposeType = Arrays.array(PurposeType._테스트, PurposeType._2D캐드);

        Cpu cpu1 = cpuRepository.findByModel("i5-1145G7").get(0);
        Cpu cpu2 = cpuRepository.findByModel("i9-10900X").get(0);
        Cpu cpu3 = cpuRepository.findByModel("Ryzen 9 5900HS").get(0);
        Gpu gpu1 = gpuRepository.findByModelAndVram("GTX 1050", 4).get(0);
        Gpu gpu2 = gpuRepository.findByModelAndVram("Radeon RX 580", 8).get(0);
        Integer ram1 = 4;
        Integer ram2 = 8;

        //when
        purposeService.add_require_ram(
                purposeType[0],
                ram1,
                Os.window,
                SpecLevel.최소사양
        );
        purposeService.add_require_ram(
                purposeType[1],
                ram2,
                Os.window,
                SpecLevel.최소사양
        );
        purposeService.add_require_cpu(
                purposeType[0],
                cpu1,
                Os.window,
                SpecLevel.최소사양
        );
        purposeService.add_require_cpu(
                purposeType[0],
                cpu2,
                Os.window,
                SpecLevel.최소사양
        );
        purposeService.add_require_cpu(
                purposeType[1],
                cpu1,
                Os.window,
                SpecLevel.최소사양
        );
        purposeService.add_require_cpu(
                purposeType[1],
                cpu3,
                Os.window,
                SpecLevel.최소사양
        );

        purposeService.add_require_gpu(
                purposeType[0],
                gpu1,
                Os.window,
                SpecLevel.최소사양
        );
        purposeService.add_require_gpu(
                purposeType[1],
                gpu2,
                Os.window,
                SpecLevel.최소사양
        );

        //when

        ScoreCondition scoreCondition = purposeService.select_ScoreCondition_from_purposeType_array(purposeType, Os.window, SpecLevel.최소사양);


        assertThat(22742).isEqualTo(scoreCondition.getCpuCondition().get(CpuType.INTEL).get());
        assertThat(23165).isEqualTo(scoreCondition.getCpuCondition().get(CpuType.AMD).get());
        assertThat(5258).isEqualTo(scoreCondition.getGpuCondition().get(GpuType.NVIDIA).get());
        assertThat(8803).isEqualTo(scoreCondition.getGpuCondition().get(GpuType.AMD).get());
        assertThat(ram2).isEqualTo(scoreCondition.getRam());
    }

    @Test
    void 전공에_따른_프로그램_검색() {
        //given
        MajorType majorType = MajorType.컴퓨터공학과;

        //when
        List<PurposeType> purposes = purposeService.select_purposes_from_major(majorType);

        //then
        assertThat(purposes.size()).isEqualTo(4);
    }

    @Test
    void 사용못하는_운영체제_검사() {
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