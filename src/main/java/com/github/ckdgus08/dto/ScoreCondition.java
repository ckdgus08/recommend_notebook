package com.github.ckdgus08.dto;

import com.github.ckdgus08.domain.enum_.CpuType;
import com.github.ckdgus08.domain.enum_.GpuType;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreCondition {
    Map<CpuType, Integer> cpuCondition = new HashMap<>();
    Map<GpuType, Integer> gpuCondition = new HashMap<>();
    Integer ram;

}
