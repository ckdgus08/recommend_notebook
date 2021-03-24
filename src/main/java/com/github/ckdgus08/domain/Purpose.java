package com.github.ckdgus08.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.ckdgus08.domain.enum_.PurposeType;
import com.github.ckdgus08.domain.enum_.Status;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(of = {"purposeType", "status"})
public class Purpose extends BaseEntity {
    //  윈도우 맥
    //  최소사양 권장사양
    //  지원가능한 gpu목록, cpu 목록
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "puporse_id")
    private Long id;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private PurposeType purposeType;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonIgnore
    @OneToMany(mappedBy = "purpose")
    private final List<PurposeOs> purposeOses = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "purpose")
    private final List<PurposeCpu> purposeCpus = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "purpose")
    private final List<PurposeGpu> purposeGpus = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "purpose")
    private final List<PurposeRam> purposeRams = new ArrayList<>();

    public Purpose(PurposeType purposeType) {
        this.id = null;
        this.status = Status.NONE;
        this.purposeType = purposeType;
    }
}