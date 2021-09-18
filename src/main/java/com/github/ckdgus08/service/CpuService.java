package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.Cpu;
import com.github.ckdgus08.repository.CpuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CpuService {

    private final CpuRepository cpuRepository;

    @Transactional
    public Long save(Cpu cpu) {
        validateDuplicate(cpu);
        Cpu result = cpuRepository.save(cpu);
        return result.getId();
    }

    public void validateDuplicate(Cpu cpu) {
        List<Cpu> result = cpuRepository.findByModel(cpu.getModel());
        if (!result.isEmpty()) throw new IllegalStateException("이미 등록된 모델입니다.");
    }
}
