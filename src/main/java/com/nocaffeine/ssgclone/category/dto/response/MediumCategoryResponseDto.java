package com.nocaffeine.ssgclone.category.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediumCategoryResponseDto {
    private Long mediumCategoryId;
    private String mediumCategoryName;
}