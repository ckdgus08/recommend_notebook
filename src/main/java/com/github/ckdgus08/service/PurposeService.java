package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.*;
import com.github.ckdgus08.domain.enum_.OS;
import com.github.ckdgus08.domain.enum_.PurposeType;
import com.github.ckdgus08.domain.enum_.SpecLevel;
import com.github.ckdgus08.repository.PurposeCpuRepository;
import com.github.ckdgus08.repository.PurposeGpuRepository;
import com.github.ckdgus08.repository.PurposeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PurposeService {

    private final PurposeRepository purposeRepository;
    private final PurposeCpuRepository purposeCpuRepository;
    private final PurposeGpuRepository purposeGpuRepository;

    @Transactional
    public Cpu add_require_cpu(PurposeType purposeType, Cpu cpu, OS os, SpecLevel specLevel) {

        Optional<Purpose> purpose = purposeRepository.findByPurposeType(purposeType);

        List<PurposeCpu> cpu_list = purpose.get().getPurposeCpus().stream()
                .filter(registered_cpu -> registered_cpu.getOs() == os)
                .filter(registered_cpu -> registered_cpu.getSpecLevel() == specLevel)
                .filter(registered_cpu -> registered_cpu.getCpu().getScore() == cpu.getScore())
                .collect(Collectors.toList());

        if (cpu_list.size() == 0) {
            PurposeCpu purposeCpu = new PurposeCpu(purpose.get(), cpu, os, specLevel);
            purposeCpuRepository.save(purposeCpu);
            return cpu;
        }
        throw new IllegalStateException("이미 등록된 CPU입니다.");
    }

    @Transactional
    public Gpu add_require_gpu(PurposeType purposeType, Gpu gpu, OS os, SpecLevel specLevel) {

        Optional<Purpose> purpose = purposeRepository.findByPurposeType(purposeType);

        List<PurposeGpu> gpu_list = purpose.get().getPurposeGpus().stream()
                .filter(registered_gpu -> registered_gpu.getOs() == os)
                .filter(registered_gpu -> registered_gpu.getSpecLevel() == specLevel)
                .filter(registered_gpu -> registered_gpu.getGpu().getScore() == gpu.getScore())
                .collect(Collectors.toList());

        if (gpu_list.size() == 0) {
            PurposeGpu purposeGpu = new PurposeGpu(purpose.get(), gpu, os, specLevel);
            purposeGpuRepository.save(purposeGpu);
            return gpu;
        }
        throw new IllegalStateException("이미 등록된 GPU입니다.");
    }

}
