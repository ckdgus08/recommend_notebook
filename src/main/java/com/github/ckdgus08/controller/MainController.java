package com.github.ckdgus08.controller;

import com.github.ckdgus08.dto.SearchCondition;
import com.github.ckdgus08.service.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final NotebookService notebookService;

    public void search_notebook(SearchCondition searchCondition) {
    }
}
