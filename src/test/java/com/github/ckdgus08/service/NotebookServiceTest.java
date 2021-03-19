package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.Notebook;
import com.github.ckdgus08.domain.enum_.Company;
import com.github.ckdgus08.domain.enum_.OS;
import com.github.ckdgus08.repository.NotebookRepository;
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
public class NotebookServiceTest {

    @Autowired
    public EntityManager em;
    @Autowired
    public NotebookService notebookService;
    @Autowired
    public NotebookRepository notebookRepository;

    @Test
    void 노트북_저장() {
        //given
        Notebook notebook1 = Notebook.builder()
                .model("삼성전자 노트북5 metal NT550XAA-K28T")
                .company(Company.삼성전자)
                .inch(15.6f)
                .os(OS.none)
                .price(816630)
                .ram(8)
                .hdd(1024)
                .ssd(0)
                .weight(1.95f)
                .build();
        //when
        Long saved_id = notebookService.save(notebook1);
        List<Notebook> result = notebookRepository.findByModel("삼성전자 노트북5 metal NT550XAA-K28T");

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
                .os(OS.none)
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

        //when

        //then
    }

}
