package com.github.ckdgus08.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {

    private String userId;
    private String major;
    private String model;
    private String title;
    private String content;
}
