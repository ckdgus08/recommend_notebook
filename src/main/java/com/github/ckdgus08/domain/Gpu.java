package com.github.ckdgus08.domain;

import com.github.ckdgus08.domain.enum_.GpuType;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(of = {"company", "model", "vram", "display", "score"})
public class Gpu extends BaseEntity {

    @Id
    @Column(name = "gpuId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String model;

    private Integer vram;

    private Integer score;

    private Integer display;

    @Enumerated(EnumType.STRING)
    private GpuType company;

    public Gpu(String model) {
        this.model = model;
    }

    public Gpu(String model, Integer vram) {
        this.model = model;
        this.vram = vram;
    }

    public Gpu(GpuType company, Integer vram, Integer score) {
        this.company = company;
        this.vram = vram;
        this.score = score;
    }
}
