package com.github.ckdgus08.service;

import com.github.ckdgus08.repository.GpuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GPUService {

    private final GpuRepository gpuRepository;

}
