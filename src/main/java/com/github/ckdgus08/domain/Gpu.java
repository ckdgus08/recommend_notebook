package com.github.ckdgus08.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString(of = {"company", "name", "vram", "display", "score"})
public class Gpu extends BaseEntity {

    @Id
    @Column(name = "gpu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    private Integer vram;

    private Integer score;

    private Integer display;

    @Column(length = 10)
    private String company;

    public Gpu(String name) {
        this.name = name;
    }

    public Gpu(String name, Integer vram) {
        this.name = name;
        this.vram = vram;
    }

    public Gpu(String company, Integer vram, Integer score) {
        this.company = company;
        this.vram = vram;
        this.score = score;
    }
}
