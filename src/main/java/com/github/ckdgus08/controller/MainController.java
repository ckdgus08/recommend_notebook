package com.github.ckdgus08.controller;

import com.github.ckdgus08.service.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final NotebookService notebookService;


    @GetMapping("/")
    public String main(Model model) {

        return "main";
    }

}
