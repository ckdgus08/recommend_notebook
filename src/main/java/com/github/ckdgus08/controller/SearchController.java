package com.github.ckdgus08.controller;

import com.github.ckdgus08.domain.Notebook;
import com.github.ckdgus08.domain.enum_.*;
import com.github.ckdgus08.dto.ScoreCondition;
import com.github.ckdgus08.service.NotebookService;
import com.github.ckdgus08.service.PurposeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final NotebookService notebookService;
    private final PurposeService purposeService;

    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(value = "_major") String major,
                         @RequestParam(value = "_purpose") String purpose,
                         @RequestParam(value = "_price", required = false) String price,
                         @RequestParam(value = "_weight", required = false) String weight,
                         @RequestParam(value = "_inch", required = false) String inch,
                         @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        // TODO: 2021/04/23 https://www.systemrequirementslab.com/cyri/requirements/diablo-iii/11243 사이트 참고해서 사양 추가

        PageRequest pageRequest = PageRequest.of(page, 10);

        List<PurposeType> purposeType = Arrays.stream(purpose.split(","))
                .map(purposeString -> PurposeType.valueOf("_" + purposeString))
                .collect(Collectors.toList());

        ScoreCondition scoreCondition = purposeService.selectScoreConditionFromPurposeTypeList(purposeType, Os.window, SpecLevel.최소사양);

        System.out.println("scoreCondition = " + scoreCondition);

        Page<Notebook> notebook = notebookService.findNotebookByScoreCondition(scoreCondition, pageRequest, null);

        if (notebook.getContent().size() == 0) return "redirect:error";

        if (scoreCondition.getCpuCondition().containsKey(CpuType.INTEL)) {
            String cpuIntelRequire = "인텔 CPU";
            cpuIntelRequire += " " + scoreCondition.getCpuCondition().get(CpuType.INTEL).get() + "점";

            model.addAttribute("cpuIntelRequire", cpuIntelRequire);
        } else {
            model.addAttribute("cpuIntelRequire", "인텔 CPU 데이터 없음");
        }
        if (scoreCondition.getCpuCondition().containsKey(CpuType.AMD)) {
            String cpuAmdRequire = "AMD CPU";
            cpuAmdRequire += " " + scoreCondition.getCpuCondition().get(CpuType.AMD).get() + "점";

            model.addAttribute("cpuAmdRequire", cpuAmdRequire);
        } else {
            model.addAttribute("cpuAmdRequire", "AMD CPU 데이터 없음");

        }

        if (scoreCondition.getGpuCondition().containsKey(GpuType.NVIDIA)) {
            String gpuNvidiaRequire = "NVIDIA GPU";

            gpuNvidiaRequire += " " + scoreCondition.getGpuCondition().get(GpuType.NVIDIA).get() + "점";

            model.addAttribute("gpuNvidiaRequire", gpuNvidiaRequire);
        } else {
            model.addAttribute("gpuNvidiaRequire", "NVIDIA GPU 데이터 없음");
        }

        if (scoreCondition.getGpuCondition().containsKey(GpuType.AMD)) {
            String gpuAmdRequire = "AMD GPU";

            gpuAmdRequire += " " + scoreCondition.getGpuCondition().get(GpuType.AMD).get() + "점";

            model.addAttribute("gpuAmdRequire", gpuAmdRequire);
        } else {
            model.addAttribute("gpuAmdRequire", "AMD GPU 데이터 없음");
        }

        model.addAttribute("notebook", notebook.getContent());
        model.addAttribute("purpose", purpose);
        model.addAttribute("all", notebook);
        model.addAttribute("ram", scoreCondition.getRam() + "GB");
        return "search";
    }
}
