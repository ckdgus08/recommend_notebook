package com.github.ckdgus08.domain;

import com.github.ckdgus08.domain.enum_.Os;
import com.github.ckdgus08.domain.enum_.SpecLevel;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(of = {"id", "os", "specLevel", "cpu"})
public class PurposeCpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purpose_id")
    private Purpose purpose;

    @Enumerated(EnumType.STRING)
    private Os os;

    @Enumerated(EnumType.STRING)
    private SpecLevel specLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cpu_id")
    private Cpu cpu;

    public PurposeCpu(Purpose purpose, Cpu cpu, Os os, SpecLevel specLevel) {
        this.cpu = cpu;
        this.os = os;
        this.specLevel = specLevel;
        if (purpose != null)
            addCpuToPurpose(purpose);
    }

    public PurposeCpu(Purpose purpose, Os os, SpecLevel specLevel) {
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
