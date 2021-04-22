package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.Notebook;
import com.github.ckdgus08.dto.ScoreCondition;
import com.github.ckdgus08.dto.SearchCondition;
import com.github.ckdgus08.repository.NotebookPurposeRepository;
import com.github.ckdgus08.repository.NotebookRepository;
import com.github.ckdgus08.repository.PurposeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotebookService {

    private final NotebookRepository notebookRepository;
    private final PurposeRepository purposeRepository;
    private final PurposeService purposeService;
    private final NotebookPurposeRepository notebookPurposeRepository;

    @Transactional
    public Long save(Notebook notebook) {
        validateDuplicate(notebook);
        Notebook result = notebookRepository.save(notebook);
        return result.getId();
    }


    public void validateDuplicate(Notebook notebook) {
        List<Notebook> result = notebookRepository.findByModel(notebook.getModel());
        if (!result.isEmpty()) throw new IllegalStateException("이미 등록된 모델입니다.");
    }


    public Page<Notebook> findNotebookByScoreCondition(ScoreCondition scoreCondition, Pageable pageable, SearchCondition searchCondition) {
        Page<Notebook> result = notebookRepository.findByScoreCondition(scoreCondition, pageable);
        // TODO: 2021/04/05 searchCondition에 의해 정렬
        return result;
    }

    public List<Notebook> search_notebook(SearchCondition searchConditionc) {

        return Arrays.asList();

    }
}
