package com.github.ckdgus08.repository;

import com.github.ckdgus08.domain.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotebookRepository extends JpaRepository<Notebook, Long>, NotebookRepositoryCustom {

    List<Notebook> findByModel(String model);


}
