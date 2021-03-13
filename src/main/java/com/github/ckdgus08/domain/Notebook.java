package com.github.ckdgus08.domain;

import com.github.ckdgus08.domain.enum_.Company;
import com.github.ckdgus08.domain.enum_.OS;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "model", "price", "weight", "inch", "company", "ram", "ssd", "cpu", "gpu"})
public class Notebook extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notebook_id")
    private Long id;
    @Column(length = 50)
    private String model;
    private Integer price;
    private Float weight;

    private Float inch;

    private Company company;

    private OS os;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cpu_id")
    private Cpu cpu;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gpu_id")
    private Gpu gpu;

    private Integer ram;
    private Integer ssd;
    private Integer hdd;

    @OneToMany(mappedBy = "notebook")
    private List<NotebookPurpose> notebookPurposes = new ArrayList<>();

    @OneToMany(mappedBy = "notebook")
    private List<Review> reviews = new ArrayList<>();

    public Notebook(Company company) {
        this.company = company;
    }

    public Notebook(Company company, String model, Float inch, Float weight, Integer ram, Integer ssd, Integer hdd, Integer price, OS os, Cpu cpu, Gpu gpu) {
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

    public Notebook update(Company company, String model, Float inch, Float weight, Integer ram, Integer ssd, Integer hdd, Integer price, OS os, Cpu cpu, Gpu gpu) {
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
