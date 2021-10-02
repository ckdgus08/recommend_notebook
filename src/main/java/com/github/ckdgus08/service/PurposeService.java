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
    public Cpu addRequireCpu(PurposeType purposeType, Cpu cpu, Os os, SpecLevel specLevel) {

        Optional<Purpose> purpose = purposeRepository.findByPurposeType(purposeType);

        List<PurposeCpu> cpuList = purpose.get().getPurposeCpus().stream()
                .filter(registeredCpu -> registeredCpu.getOs() == os)
                .filter(registeredCpu -> registeredCpu.getSpecLevel() == specLevel)
                .filter(registeredCpu -> registeredCpu.getCpu().getScore().equals(cpu.getScore()))
                .collect(Collectors.toList());

        if (cpuList.isEmpty()) {
            PurposeCpu purposeCpu = new PurposeCpu(purpose.get(), cpu, os, specLevel);
            purposeCpuRepository.save(purposeCpu);
            return cpu;
        }
        throw new IllegalStateException("이미 등록된 CPU입니다.");
    }

    @Transactional
    public Gpu addRequireGpu(PurposeType purposeType, Gpu gpu, Os os, SpecLevel specLevel) {

        Optional<Purpose> purpose = purposeRepository.findByPurposeType(purposeType);

        List<PurposeGpu> gpuList = purpose.get().getPurposeGpus().stream()
                .filter(registeredGpu -> registeredGpu.getOs() == os)
                .filter(registeredGpu -> registeredGpu.getSpecLevel() == specLevel)
                .filter(registeredGpu -> registeredGpu.getGpu().getScore().equals(gpu.getScore()))
                .collect(Collectors.toList());

        if (gpuList.isEmpty()) {
            PurposeGpu purposeGpu = new PurposeGpu(purpose.get(), gpu, os, specLevel);
            purposeGpuRepository.save(purposeGpu);
            return gpu;
        }
        throw new IllegalStateException("이미 등록된 GPU입니다.");
    }

    @Transactional
    public Integer addRequireRam(PurposeType purposeType, Integer ram, Os os, SpecLevel specLevel) {

        Optional<Purpose> purpose = purposeRepository.findByPurposeType(purposeType);

        List<PurposeRam> gpuList = purpose.get().getPurposeRams().stream()
                .filter(registeredGpu -> registeredGpu.getOs() == os)
                .filter(registeredGpu -> registeredGpu.getSpecLevel() == specLevel)
                .filter(registeredGpu -> registeredGpu.getRam().equals(ram))
                .collect(Collectors.toList());

        if (gpuList.isEmpty()) {
            PurposeRam purposeRam = new PurposeRam(purpose.get(), ram, os, specLevel);
            purposeRamRepository.save(purposeRam);
            return ram;
        }
        throw new IllegalStateException("이미 등록된 RAM입니다.");
    }

    public Map<CpuType, Optional<Integer>> selectCpuFromPurposeTypeList(List<PurposeType> purposeTypes, Os os, SpecLevel specLevel) {
        List<Purpose> purposes = purposeRepository.findByPurposeTypeList(purposeTypes);

        return purposes.stream()
                .flatMap(p -> p.getPurposeCpus().stream())
                .collect(Collectors.groupingBy(
                        cpu -> cpu.getCpu().getCompany(),
                        Collectors.mapping(c -> c.getCpu().getScore(), Collectors.maxBy(Integer::compare))
                ));
    }

    public ScoreCondition selectScoreConditionFromPurposeTypeList(List<PurposeType> purposeTypes, Os os, SpecLevel specLevel) {

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

    public Map<GpuType, Optional<Integer>> selectGpuFromPurposeTypeList(List<PurposeType> purposeTypes, Os os, SpecLevel specLevel) {
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

    public List<PurposeType> selectPurposesFromMajor(MajorType majorType) {

        return majorType.getPurposeTypes();
    }


}
