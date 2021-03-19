package com.github.ckdgus08.repository;

import com.github.ckdgus08.domain.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CpuRepository extends JpaRepository<Cpu, Long> {

    List<Cpu> findByModel(String model);

}
