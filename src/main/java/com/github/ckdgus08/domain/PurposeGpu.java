package com.github.ckdgus08.domain;

import com.github.ckdgus08.domain.enum_.OS;
import com.github.ckdgus08.domain.enum_.SpecLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@ToString(of = {"id", "os", "specLevel", "vram", "gpu_score", "rzaen_score", "gpu"})
public class PurposeGpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purpose_id")
    private Purpose purpose;

    private OS os;
    private SpecLevel specLevel;

    private Integer vram;

    private Integer gpu_score;
    private Integer razen_score;

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

    public PurposeGpu(Purpose purpose, Integer vram, Integer gpu_score, Integer razen_score, OS os, SpecLevel specLevel) {
        this.vram = vram;
        this.os = os;
        this.gpu_score = gpu_score;
        this.razen_score = razen_score;
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
