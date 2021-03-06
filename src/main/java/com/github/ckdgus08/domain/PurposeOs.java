package com.github.ckdgus08.domain;

import com.github.ckdgus08.domain.enum_.Os;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString(of = {"id", "os"})
public class PurposeOs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purposeId")
    private Purpose purpose;

    @Enumerated(EnumType.STRING)
    private Os os;

    public PurposeOs(Purpose purpose, Os os) {
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
