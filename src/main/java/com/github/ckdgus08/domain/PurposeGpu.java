package com.github.ckdgus08.domain;

import com.github.ckdgus08.domain.enum_.OS;
import com.github.ckdgus08.domain.enum_.SpecLevel;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString(of = {"id", "os", "specLevel", "gpu"})
public class PurposeGpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purpose_id")
    private Purpose purpose;

    private OS os;
    private SpecLevel specLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gpu_id")
    private Gpu gpu;

    public PurposeGpu(Purpose purpose, Gpu gpu, OS os, SpecLevel specLevel) {
        this.gpu = gpu;
        this.os = os;
        this.specLevel = specLevel;
        if (purpose != null)
            addGpuToPurpose(purpose);
    }

    public PurposeGpu(Purpose purpose, OS os, SpecLevel specLevel) {
        this.os = os;
        this.specLevel = specLevel;
        if (purpose != null)
            addGpuToPurpose(purpose);
    }

    public void addGpuToPurpose(Purpose purpose) {
        this.purpose = purpose;
        if (purpose != null)
            purpose.getPurposeGpus().add(this);
    }
}
