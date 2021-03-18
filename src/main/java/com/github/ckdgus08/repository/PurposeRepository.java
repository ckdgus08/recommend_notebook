package com.github.ckdgus08.repository;

import com.github.ckdgus08.domain.Purpose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurposeRepository extends JpaRepository<Purpose, Long>, PurposeRepositoryCustom {

}
