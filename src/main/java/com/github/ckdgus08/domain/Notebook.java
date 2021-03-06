package com.github.ckdgus08.domain;

import com.github.ckdgus08.domain.enum_.Company;
import com.github.ckdgus08.domain.enum_.Os;
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
@ToString(of = {"id", "model", "price", "weight", "inch", "company", "ram", "ssd", "cpu", "gpu"})
public class Notebook extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notebookId")
    private Long id;
    @Column(length = 50)
    private String model;
    private Integer price;
    private Float weight;

    private Float inch;

    @OneToMany(mappedBy = "notebook")
    private final List<NotebookPurpose> notebookPurposes = new ArrayList<>();

    private Os os;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cpuId")
    private Cpu cpu;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gpuId")
    private Gpu gpu;

    private Integer ram;
    private Integer ssd;
    private Integer hdd;
    @OneToMany(mappedBy = "notebook")
    private final List<Review> reviews = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Company company;

    public Notebook(Company company) {
        this.company = company;
    }

    public Notebook(Company company, String model, Float inch, Float weight, Integer ram, Integer ssd, Integer hdd, Integer price, Os os, Cpu cpu, Gpu gpu) {
        this.id = null;
        this.company = company;
        this.model = model;
        this.inch = inch;
        this.weight = weight;
        this.ram = ram;
        this.ssd = ssd;
        this.hdd = hdd;
        this.price = price;
        this.os = os;
        this.cpu = cpu;
        this.gpu = gpu;
    }

    public Notebook update(Company company, String model, Float inch, Float weight, Integer ram, Integer ssd, Integer hdd, Integer price, Os os, Cpu cpu, Gpu gpu) {
        if (inch != null)
            this.inch = inch;
        if (weight != null)
            this.weight = weight;
        if (ram != null)
            this.ram = ram;
        if (ssd != null)
            this.ssd = ssd;
        if (hdd != null)
            this.hdd = hdd;
        if (price != null)
            this.price = price;
        if (os != null)
            this.os = os;
        if (cpu != null)
            this.cpu = cpu;
        if (gpu != null)
            this.gpu = gpu;
        return this;
    }

}
