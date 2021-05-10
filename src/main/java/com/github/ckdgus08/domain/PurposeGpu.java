package com.github.ckdgus08.domain;

import com.github.ckdgus08.domain.enum_.Os;
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
    @JoinColumn(name = "purposeId")
    private Purpose purpose;

    @Enumerated(EnumType.STRING)
    private Os os;
    @Enumerated(EnumType.STRING)
    private SpecLevel specLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gpuId")
    private Gpu gpu;

    public PurposeGpu(Purpose purpose, Gpu gpu, Os os, SpecLevel specLevel) {
        this.gpu = gpu;
        this.os = os;
        this.specLevel = specLevel;
        if (purpose != null)
            addGpuToPurpose(purpose);
    }

    public PurposeGpu(Purpose purpose, Os os, SpecLevel specLevel) {
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
