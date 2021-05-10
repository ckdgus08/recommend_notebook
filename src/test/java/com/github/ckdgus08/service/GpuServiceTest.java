package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.Gpu;
import com.github.ckdgus08.domain.enum_.GpuType;
import com.github.ckdgus08.repository.GpuRepository;
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
public class GpuServiceTest {

    @Autowired
    public EntityManager em;
    @Autowired
    public GpuService gpuService;
    @Autowired
    public GpuRepository gpuRepository;

    @Test
    void GPU_저장() {
        //given
        Gpu gpu = Gpu.builder()
                .company(GpuType.NVIDIA)
                .display(4)
                .model("GTX 테스트-01")
                .score(5258)
                .vram(4)
                .build();
        //when
        Long savedId = gpuService.save(gpu);
        List<Gpu> result = gpuRepository.findByModelAndVram("GTX 테스트-01", 4);

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(savedId).isEqualTo(result.get(0).getId());
    }

    @Test
    void GPU_중복_저장() {
        //given
        Gpu gpu = Gpu.builder()
                .company(GpuType.NVIDIA)
                .display(4)
                .model("GTX 테스트-01")
                .score(5258)
                .vram(4)
                .build();
        //when
        Long savedId1 = gpuService.save(gpu);

        assertThatIllegalStateException().isThrownBy(
                () -> gpuService.save(gpu)
        );
    }


}
