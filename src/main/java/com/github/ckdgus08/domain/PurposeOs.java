package com.github.ckdgus08.domain;

import com.github.ckdgus08.domain.enum_.OS;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@ToString(of = {"id", "os"})
public class PurposeOs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purpose_id")
    private Purpose purpose;

    private OS os;

    public PurposeOs(Purpose purpose, OS os) {
        this.os = os;
        if (purpose != null)
            addOsToPurpose(purpose);
    }

    public void addOsToPurpose(Purpose purpose) {
        this.purpose = purpose;
        if (purpose != null)
            purpose.getPurposeOses().add(this);
    }
}
