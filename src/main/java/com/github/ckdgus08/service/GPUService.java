package com.github.ckdgus08.service;

import com.github.ckdgus08.domain.Gpu;
import com.github.ckdgus08.repository.GpuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GPUService {

    private final GpuRepository gpuRepository;

    @Transactional
    public Long save(Gpu gpu) {
        validateDuplicate(gpu);
        Gpu result = gpuRepository.save(gpu);
        return result.getId();
    }

    public void validateDuplicate(Gpu gpu) {
        List<Gpu> result = gpuRepository.findByModelAndVram(gpu.getModel(), gpu.getVram());
        if (result.size() != 0) throw new IllegalStateException("이미 등록된 모델입니다.");
    }
}
