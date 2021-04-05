package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.Cpu;
import com.github.ckdgus08.domain.Gpu;
import com.github.ckdgus08.domain.Notebook;
import com.github.ckdgus08.domain.enum_.Company;
import com.github.ckdgus08.domain.enum_.CpuType;
import com.github.ckdgus08.domain.enum_.GpuType;
import com.github.ckdgus08.domain.enum_.Os;
import com.github.ckdgus08.dto.ScoreCondition;
import com.github.ckdgus08.dto.SearchCondition;
import com.github.ckdgus08.repository.NotebookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

@SpringBootTest
@Transactional
public class NotebookServiceTest {

    @Autowired
    public EntityManager em;
    @Autowired
    public NotebookService notebookService;
    @Autowired
    public NotebookRepository notebookRepository;

    @BeforeEach
    void 데이터_삽입() {
        //given

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

        Notebook notebook1 = Notebook.builder()
                .model("삼성전자 노트북5 metal NT550XAA-K28T")
                .company(Company.삼성전자)
                .inch(15.6f)
                .os(Os.none)
                .price(999999)
                .ram(8)
                .hdd(1024)
                .ssd(0)
                .weight(1.95f)
                .cpu(cpu2)
                .gpu(gpu1)
                .build();
        em.persist(notebook1);

        Notebook notebook2 = Notebook.builder()
                .model("삼성전자 노트북5 metal")
                .company(Company.삼성전자)
                .inch(15.6f)
                .os(Os.none)
                .price(888888)
                .ram(8)
                .hdd(1024)
                .ssd(0)
                .weight(1.95f)
                .cpu(cpu3)
                .gpu(gpu2)
                .build();
        em.persist(notebook2);
        //when

        //then
    }

    @Test
    void 노트북_저장() {
        //given
        Notebook notebook2 = Notebook.builder()
                .model("테스트테스트")
                .company(Company.삼성전자)
                .inch(15.6f)
                .os(Os.none)
                .price(816630)
                .ram(8)
                .hdd(1024)
                .ssd(0)
                .weight(1.95f)
                .build();
        //when
        Long saved_id = notebookService.save(notebook2);
        List<Notebook> result = notebookRepository.findByModel("테스트테스트");

        //then
        assertThat(result.size()).isEqualTo(1);
        assertThat(saved_id).isEqualTo(result.get(0).getId());
    }

    @Test
    void 노트북_중복_저장() {
        //given
        Notebook notebook1 = Notebook.builder()
                .model("삼성전자 노트북5 metal NT550XAA-K28T")
                .company(Company.삼성전자)
                .inch(15.6f)
                .os(Os.none)
                .price(816630)
                .ram(8)
                .hdd(1024)
                .ssd(0)
                .weight(1.95f)
                .build();
        //when
        Long saved_id1 = notebookService.save(notebook1);

        assertThatIllegalStateException().isThrownBy(
                () -> notebookService.save(notebook1)
        );
    }

    @Test
    void 사양별_노트북목록_검색() {
        //given

        Map<CpuType, Optional<Integer>> cpu_map = new HashMap<>();
        cpu_map.put(CpuType.INTEL, Optional.of(22742));
        cpu_map.put(CpuType.AMD, Optional.of(10000));

        ScoreCondition scoreCondition = new ScoreCondition();
        scoreCondition.setCpuCondition(cpu_map);
        Pageable pageable = PageRequest.of(0, 50);

        //when
        Page<Notebook> result = notebookService.findNotebookByScoreCondition(scoreCondition, pageable, null);

        //then
        assertThat(result.getContent().get(0).getModel()).isEqualTo("삼성전자 노트북5 metal NT550XAA-K28T");
        assertThat(result.getContent().get(1).getModel()).isEqualTo("삼성전자 노트북5 metal");
    }


    @Test
    void 검색정렬순서() {
        //given
        SearchCondition searchCondition = SearchCondition.builder()
                .search_order(Map.of(0, "가격"))
                .build();

        Map<CpuType, Optional<Integer>> cpu_map = new HashMap<>();
        cpu_map.put(CpuType.INTEL, Optional.of(22742));
        cpu_map.put(CpuType.AMD, Optional.of(10000));

        ScoreCondition scoreCondition = new ScoreCondition();
        scoreCondition.setCpuCondition(cpu_map);
        Pageable pageable = PageRequest.of(0, 50);

        //when
        Page<Notebook> result1 = notebookService.findNotebookByScoreCondition(scoreCondition, pageable, null);
        Page<Notebook> result2 = notebookService.findNotebookByScoreCondition(scoreCondition, pageable, searchCondition);
        //then

        List<Notebook> result1_sorted = result1.stream()
                .sorted(Comparator.comparingInt(Notebook::getPrice))
                .collect(Collectors.toList());

        System.out.println("result1_sorted = " + result1_sorted);

        assertThat(result2.getContent().get(0)).isEqualTo(result1_sorted.get(0));
    }

    @Test
    void 검색후_같은전공_리뷰검증() {
        //given

        //when

        //then
    }

}
