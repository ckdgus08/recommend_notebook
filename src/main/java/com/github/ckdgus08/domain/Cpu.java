package com.github.ckdgus08.domain;

import com.github.ckdgus08.domain.enum_.CpuType;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(of = {"company", "model", "core", "thread", "originGhz", "maxGhz", "score"})
public class Cpu extends BaseEntity {

    @Id
    @Column(name = "cpuId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CpuType company;

    @Column(length = 25)
    private String codeName;

    @Column(length = 40)
    private String model;

    @Column(length = 15)
    private String generation;

    private Integer nm;
    private Integer core;
    private Integer thread;
    private Float originGhz;
    private Float maxGhz;
    private Float cache;

    private Integer score;

    public Cpu(CpuType company, String generation, String codeName, String model, Integer core, Integer score) {
        this.id = null;
        this.company = company;
        this.generation = generation;
        this.model = model;
        this.core = core;
        this.codeName = codeName;
        this.score = score;
    }

    public Cpu(CpuType company, Integer core, Float originGhz, Integer score) {
        this.id = null;
        this.company = company;
        this.core = core;
        this.originGhz = originGhz;
        this.score = score;
    }

    public Cpu(CpuType company) {
        this.company = company;
    }
}
