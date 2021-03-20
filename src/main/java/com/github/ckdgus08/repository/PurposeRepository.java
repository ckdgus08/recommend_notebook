package com.github.ckdgus08.repository;

import com.github.ckdgus08.domain.Purpose;
import com.github.ckdgus08.domain.enum_.PurposeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurposeRepository extends JpaRepository<Purpose, Long>, PurposeRepositoryCustom {

    Optional<Purpose> findByPurposeType(PurposeType purposeType);
}
