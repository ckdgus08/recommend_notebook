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
@ToString(of = {"company", "model", "core", "thread", "origin_ghz", "max_ghz", "score"})
public class Cpu extends BaseEntity {

    @Id
    @Column(name = "cpu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5)
    private CpuType company;

    @Column(length = 25)
    private String code_name;

    @Column(length = 40)
    private String model;

    @Column(length = 15)
    private String generation;

    private Integer nm;
    private Integer core;
    private Integer thread;
    private Float origin_ghz;
    private Float max_ghz;
    private Float cache;

    private Integer score;

    public Cpu(CpuType company, String generation, String code_name, String model, Integer core, Integer score) {
        this.id = null;
        this.company = company;
        this.generation = generation;
        this.model = model;
        this.core = core;
        this.code_name = code_name;
        this.score = score;
    }

    public Cpu(CpuType company, Integer core, Float origin_ghz, Integer score) {
        this.id = null;
        this.company = company;
        this.core = core;
        this.origin_ghz = origin_ghz;
        this.score = score;
    }

    public Cpu(CpuType company) {
        this.company = company;
    }
}
