package com.github.ckdgus08.repository;

import com.github.ckdgus08.domain.Purpose;
import com.github.ckdgus08.domain.enum_.PurposeType;

import java.util.List;

public interface PurposeRepositoryCustom {

    List<Purpose> findByPurposeTypeList(List<PurposeType> purposeTypes);

}
