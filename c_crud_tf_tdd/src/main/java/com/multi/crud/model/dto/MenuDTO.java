package com.multi.crud.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDTO {

    private int code;
    private String name;
    private int price;
    private String orderableStatus;

    // Category와 연관관계 설정 (카테고리 코드 대신 CategoryDTO 객체 사용)
    private CategoryDTO category;
}
