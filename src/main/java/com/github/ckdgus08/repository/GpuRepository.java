package com.github.ckdgus08.repository;

import com.github.ckdgus08.domain.Gpu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GpuRepository extends JpaRepository<Gpu, Long> {

    List<Gpu> findByModelAndVram(String model, Integer vram);

}
