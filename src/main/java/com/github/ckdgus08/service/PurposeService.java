package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.*;
import com.github.ckdgus08.domain.enum_.*;
import com.github.ckdgus08.repository.PurposeCpuRepository;
import com.github.ckdgus08.repository.PurposeGpuRepository;
import com.github.ckdgus08.repository.PurposeRamRepository;
import com.github.ckdgus08.repository.PurposeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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

        if (cpu_list.size() == 0) {
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

        if (gpu_list.size() == 0) {
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

        if (gpu_list.size() == 0) {
            PurposeRam purposeRam = new PurposeRam(purpose.get(), ram, os, specLevel);
            purposeRamRepository.save(purposeRam);
            return ram;
        }
        throw new IllegalStateException("이미 등록된 RAM입니다.");
    }

    public Map<CpuType, Set<Integer>> select_cpu_from_purposeType_array(PurposeType[] purposeTypes, Os os, SpecLevel specLevel) {
        List<Purpose> purposes = purposeRepository.findByPurposeTypeArray(purposeTypes);

        return purposes.stream()
                .flatMap(p -> p.getPurposeCpus().stream())
                .filter(c -> c.getOs() == os)
                .filter(c -> c.getSpecLevel() == specLevel)
                .collect(Collectors.groupingBy(
                        c -> c.getCpu().getCompany(),
                        HashMap::new,
                        Collectors.mapping(c -> c.getCpu().getScore(), Collectors.toSet())
                ));

        // TODO: 2021/03/22 Map<CpuType, Set<Integer>> -> Map<CpuType, Integer> 로 한번에 처리하는 방법 생각
    }

    public Map<GpuType, Set<Integer>> select_gpu_from_purposeType_array(PurposeType[] purposeTypes, Os os, SpecLevel specLevel) {
        List<Purpose> purposes = purposeRepository.findByPurposeTypeArray(purposeTypes);

        return purposes.stream()
                .flatMap(p -> p.getPurposeGpus().stream())
                .filter(c -> c.getOs() == os)
                .filter(c -> c.getSpecLevel() == specLevel)
                .collect(Collectors.groupingBy(
                        c -> c.getGpu().getCompany(),
                        HashMap::new,
                        Collectors.mapping(c -> c.getGpu().getScore(), Collectors.toSet())
                ));
    }

    public Integer select_ram_from_purposeType_array(PurposeType[] purposeTypes, Os os, SpecLevel specLevel) {
        List<Purpose> purposes = purposeRepository.findByPurposeTypeArray(purposeTypes);

        return purposes.stream()
                .flatMap(p -> p.getPurposeRams().stream())
                .filter(c -> c.getOs() == os)
                .filter(c -> c.getSpecLevel() == specLevel)
                .map(PurposeRam::getRam)
                .max(Integer::compare).get();
    }


}
