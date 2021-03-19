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
@ToString(of = {"id", "os", "specLevel", "ram"})
public class PurposeRam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purpose_id")
    private Purpose purpose;

    private OS os;
    private SpecLevel specLevel;

    private Integer ram;

    public PurposeRam(Purpose purpose, Integer ram, OS os, SpecLevel specLevel) {
        this.ram = ram;
        this.os = os;
        this.specLevel = specLevel;
        if (purpose != null)
            addRamToPurpose(purpose);
    }

    public void addRamToPurpose(Purpose purpose) {
        this.purpose = purpose;
        if (purpose != null)
            purpose.getPurposeRams().add(this);
    }
}
