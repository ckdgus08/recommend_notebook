package com.github.ckdgus08.dto;

import com.github.ckdgus08.domain.enum_.CpuType;
import com.github.ckdgus08.domain.enum_.GpuType;
import lombok.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreCondition {
    Map<CpuType, Optional<Integer>> cpuCondition = new HashMap<>();
    Map<GpuType, Optional<Integer>> gpuCondition = new HashMap<>();
    Integer ram;

}
