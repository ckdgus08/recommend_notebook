package com.github.ckdgus08.repository;

import com.github.ckdgus08.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {


}
