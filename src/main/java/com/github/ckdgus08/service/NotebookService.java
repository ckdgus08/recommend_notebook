package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.Notebook;
import com.github.ckdgus08.repository.NotebookPurposeRepository;
import com.github.ckdgus08.repository.NotebookRepository;
import com.github.ckdgus08.repository.PurposeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (result.size() != 0) throw new IllegalStateException("이미 등록된 모델입니다.");
    }


}
