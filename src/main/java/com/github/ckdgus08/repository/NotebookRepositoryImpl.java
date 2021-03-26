package com.github.ckdgus08.repository;

import com.github.ckdgus08.domain.Notebook;
import com.github.ckdgus08.domain.enum_.CpuType;
import com.github.ckdgus08.domain.enum_.GpuType;
import com.github.ckdgus08.dto.ScoreCondition;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import static com.github.ckdgus08.domain.QCpu.cpu;
import static com.github.ckdgus08.domain.QGpu.gpu;
import static com.github.ckdgus08.domain.QNotebook.notebook;

public class NotebookRepositoryImpl implements NotebookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public NotebookRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Notebook> findByScoreCondition(ScoreCondition condition, Pageable pageable) {
        QueryResults<Notebook> result = queryFactory.selectFrom(notebook)
                .join(notebook.cpu, cpu).fetchJoin()
                .join(notebook.gpu, gpu)
                .where(
                        cpuGoe(condition),
                        gpuGoe(condition),
                        ramGoe(condition)
                ).limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();
        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    private Predicate cpuGoe(ScoreCondition condition) {

        BooleanBuilder intel_builder = new BooleanBuilder();
        BooleanBuilder amd_builder = new BooleanBuilder();

        if (condition.getCpuCondition().containsKey(CpuType.INTEL)) {
            intel_builder.and(notebook.cpu.company.eq(CpuType.INTEL))
                    .and(notebook.cpu.score.goe(condition.getCpuCondition().get(CpuType.INTEL).get()));
        }
        if (condition.getCpuCondition().containsKey(CpuType.AMD)) {
            amd_builder.and(notebook.cpu.company.eq(CpuType.AMD))
                    .and(notebook.cpu.score.goe(condition.getCpuCondition().get(CpuType.AMD).get()));
        }

        return (intel_builder.or(amd_builder));
    }

    private Predicate gpuGoe(ScoreCondition condition) {

        BooleanBuilder nvidia_builder = new BooleanBuilder();
        BooleanBuilder amd_builder = new BooleanBuilder();

        if (condition.getCpuCondition().containsKey(GpuType.NVIDIA)) {
            nvidia_builder.and(notebook.gpu.company.eq(GpuType.NVIDIA))
                    .and(notebook.gpu.score.goe(condition.getCpuCondition().get(GpuType.NVIDIA).get()));
        }
        if (condition.getCpuCondition().containsKey(GpuType.AMD)) {
            amd_builder.and(notebook.gpu.company.eq(GpuType.AMD))
                    .and(notebook.gpu.score.goe(condition.getCpuCondition().get(GpuType.AMD).get()));
        }

        return (nvidia_builder.or(amd_builder));
    }

    private Predicate ramGoe(ScoreCondition condition) {

        BooleanBuilder ram_builder = new BooleanBuilder();

        if (condition.getRam() != null) {
            ram_builder.and(notebook.ram.goe(condition.getRam()));
        }

        return ram_builder;
    }
}
