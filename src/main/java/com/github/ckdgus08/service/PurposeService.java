package com.github.ckdgus08.service;

import com.github.ckdgus08.repository.PurposeRepository;
import com.github.ckdgus08.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PurposeService {

    private final PurposeRepository purposeRepository;
    private final Utils utils;

}
