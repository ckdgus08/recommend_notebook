package com.github.ckdgus08.service;

import com.github.ckdgus08.repository.NotebookPurposeRepository;
import com.github.ckdgus08.repository.NotebookRepository;
import com.github.ckdgus08.repository.PurposeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotebookService {

    private final NotebookRepository notebookRepository;
    private final PurposeRepository purposeRepository;
    private final PurposeService purposeService;
    private final NotebookPurposeRepository notebookPurposeRepository;

}
