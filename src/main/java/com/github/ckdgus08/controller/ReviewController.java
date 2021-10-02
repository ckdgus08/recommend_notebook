package com.github.ckdgus08.controller;

import com.github.ckdgus08.domain.Notebook;
import com.github.ckdgus08.domain.enum_.MajorType;
import com.github.ckdgus08.repository.NotebookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final NotebookRepository notebookRepository;

    @GetMapping("/review")
    public String review(Model model) {

        model.addAttribute("majorTypes", MajorType.values());
        model.addAttribute("models", notebookRepository.findAll()
                .stream()
                .map(Notebook::getModel)
                .collect(Collectors.toList())
        );

        return "review";
    }

}
