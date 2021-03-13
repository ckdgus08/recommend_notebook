package com.github.ckdgus08.domain;

import com.github.ckdgus08.domain.enum_.OS;
import com.github.ckdgus08.domain.enum_.SpecLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "os", "specLevel", "core", "ghz", "intel_score", "amd_score", "cpu"})
public class PurposeCpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purpose_id")
    private Purpose purpose;

    private OS os;

    private SpecLevel specLevel;

    private Integer core;

    private Float ghz;

    private Integer intel_score;
    private Integer amd_score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cpu_id")
    private Cpu cpu;

    public PurposeCpu(Purpose purpose, Cpu cpu, OS os, SpecLevel specLevel) {
        this.cpu = cpu;
        this.os = os;
        this.specLevel = specLevel;
        if (purpose != null)
            addCpuToPurpose(purpose);
    }

    public PurposeCpu(Purpose purpose, Integer core, Integer intel_score, Integer amd_score, Float ghz, OS os, SpecLevel specLevel) {
        if (core != null)
            this.core = core;
        if (ghz != null)
            this.ghz = ghz;
        if (intel_score != null)
            this.intel_score = intel_score;
        if (amd_score != null)
            this.amd_score = amd_score;
        this.os = os;
        this.specLevel = specLevel;
        if (purpose != null)
            addCpuToPurpose(purpose);
    }

    public void addCpuToPurpose(Purpose purpose) {
        this.purpose = purpose;
        if (purpose != null)
            purpose.getPurposeCpus().add(this);
    }
}
