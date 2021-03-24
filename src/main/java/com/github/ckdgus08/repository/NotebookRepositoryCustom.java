package com.github.ckdgus08.repository;

import com.github.ckdgus08.domain.Notebook;
import com.github.ckdgus08.dto.ScoreCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotebookRepositoryCustom {

    Page<Notebook> findByScoreCondition(ScoreCondition scoreCondition, Pageable pageable);
}
