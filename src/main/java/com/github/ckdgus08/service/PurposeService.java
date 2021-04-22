package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.*;
import com.github.ckdgus08.domain.enum_.*;
import com.github.ckdgus08.dto.ScoreCondition;
import com.github.ckdgus08.repository.PurposeCpuRepository;
import com.github.ckdgus08.repository.PurposeGpuRepository;
import com.github.ckdgus08.repository.PurposeRamRepository;
import com.github.ckdgus08.repository.PurposeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PurposeService {

    private final PurposeRepository purposeRepository;
    private final PurposeCpuRepository purposeCpuRepository;
    private final PurposeGpuRepository purposeGpuRepository;
    private final PurposeRamRepository purposeRamRepository;

    @Transactional
    public Cpu add_require_cpu(PurposeType purposeType, Cpu cpu, Os os, SpecLevel specLevel) {

        Optional<Purpose> purpose = purposeRepository.findByPurposeType(purposeType);

        List<PurposeCpu> cpu_list = purpose.get().getPurposeCpus().stream()
                .filter(registered_cpu -> registered_cpu.getOs() == os)
                .filter(registered_cpu -> registered_cpu.getSpecLevel() == specLevel)
                .filter(registered_cpu -> registered_cpu.getCpu().getScore().equals(cpu.getScore()))
                .collect(Collectors.toList());

        if (cpu_list.isEmpty()) {
            PurposeCpu purposeCpu = new PurposeCpu(purpose.get(), cpu, os, specLevel);
            purposeCpuRepository.save(purposeCpu);
            return cpu;
        }
        throw new IllegalStateException("이미 등록된 CPU입니다.");
    }

    @Transactional
    public Gpu add_require_gpu(PurposeType purposeType, Gpu gpu, Os os, SpecLevel specLevel) {

        Optional<Purpose> purpose = purposeRepository.findByPurposeType(purposeType);

        List<PurposeGpu> gpu_list = purpose.get().getPurposeGpus().stream()
                .filter(registered_gpu -> registered_gpu.getOs() == os)
                .filter(registered_gpu -> registered_gpu.getSpecLevel() == specLevel)
                .filter(registered_gpu -> registered_gpu.getGpu().getScore().equals(gpu.getScore()))
                .collect(Collectors.toList());

        if (gpu_list.isEmpty()) {
            PurposeGpu purposeGpu = new PurposeGpu(purpose.get(), gpu, os, specLevel);
            purposeGpuRepository.save(purposeGpu);
            return gpu;
        }
        throw new IllegalStateException("이미 등록된 GPU입니다.");
    }

    @Transactional
    public Integer add_require_ram(PurposeType purposeType, Integer ram, Os os, SpecLevel specLevel) {

        Optional<Purpose> purpose = purposeRepository.findByPurposeType(purposeType);

        List<PurposeRam> gpu_list = purpose.get().getPurposeRams().stream()
                .filter(registered_gpu -> registered_gpu.getOs() == os)
                .filter(registered_gpu -> registered_gpu.getSpecLevel() == specLevel)
                .filter(registered_gpu -> registered_gpu.getRam().equals(ram))
                .collect(Collectors.toList());

        if (gpu_list.isEmpty()) {
            PurposeRam purposeRam = new PurposeRam(purpose.get(), ram, os, specLevel);
            purposeRamRepository.save(purposeRam);
            return ram;
        }
        throw new IllegalStateException("이미 등록된 RAM입니다.");
    }

    public Map<CpuType, Optional<Integer>> select_cpu_from_purposeType_list(List<PurposeType> purposeTypes, Os os, SpecLevel specLevel) {
        List<Purpose> purposes = purposeRepository.findByPurposeTypeList(purposeTypes);

        return purposes.stream()
                .flatMap(p -> p.getPurposeCpus().stream())
                .collect(Collectors.groupingBy(
                        cpu -> cpu.getCpu().getCompany(),
                        Collectors.mapping(c -> c.getCpu().getScore(), Collectors.maxBy(Integer::compare))
                ));
    }

    public ScoreCondition select_ScoreCondition_from_purposeType_list(List<PurposeType> purposeTypes, Os os, SpecLevel specLevel) {

        List<Purpose> purposes = purposeRepository.findByPurposeTypeList(purposeTypes);

        return ScoreCondition.builder()
                .cpuCondition(
                        purposes.stream()
                                .flatMap(p -> p.getPurposeCpus().stream())
                                .filter(c -> c.getOs() == os)
                                .filter(c -> c.getSpecLevel() == specLevel)
                                .filter(c -> c.getCpu() != null)
                                .collect(Collectors.groupingBy(
                                        c -> c.getCpu().getCompany(),
                                        Collectors.mapping(c -> c.getCpu().getScore(), Collectors.maxBy(Integer::compare))
                                )))
                .gpuCondition(
                        purposes.stream()
                                .flatMap(p -> p.getPurposeGpus().stream())
                                .filter(c -> c.getOs() == os)
                                .filter(c -> c.getSpecLevel() == specLevel)
                                .filter(c -> c.getGpu() != null)
                                .collect(Collectors.groupingBy(
                                        c -> c.getGpu().getCompany(),
                                        Collectors.mapping(c -> c.getGpu().getScore(), Collectors.maxBy(Integer::compare))
                                )))
                .ram(
                        purposes.stream()
                                .flatMap(p -> p.getPurposeRams().stream())
                                .filter(c -> c.getOs() == os)
                                .filter(c -> c.getSpecLevel() == specLevel)
                                .filter(c -> c.getRam() != null)
                                .map(PurposeRam::getRam)
                                .max(Integer::compare).get()
                ).build();
    }

    public Map<GpuType, Optional<Integer>> select_gpu_from_purposeType_list(List<PurposeType> purposeTypes, Os os, SpecLevel specLevel) {
        List<Purpose> purposes = purposeRepository.findByPurposeTypeList(purposeTypes);

        return purposes.stream()
                .flatMap(p -> p.getPurposeGpus().stream())
                .filter(c -> c.getOs() == os)
                .filter(c -> c.getSpecLevel() == specLevel)
                .collect(Collectors.groupingBy(
                        c -> c.getGpu().getCompany(),
                        Collectors.mapping(c -> c.getGpu().getScore(), Collectors.maxBy(Integer::compare))
                ));
    }

    public Integer select_ram_from_purposeType_list(List<PurposeType> purposeTypes, Os os, SpecLevel specLevel) {
        List<Purpose> purposes = purposeRepository.findByPurposeTypeList(purposeTypes);

        return purposes.stream()
                .flatMap(p -> p.getPurposeRams().stream())
                .filter(c -> c.getOs() == os)
                .filter(c -> c.getSpecLevel() == specLevel)
                .map(PurposeRam::getRam)
                .max(Integer::compare).get();
    }

    public List<PurposeType> select_purposes_from_major(MajorType majorType) {

        return majorType.getPurposeTypes();
    }


}
