package com.multi.spring.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class ThymeTestDto {

    private long id;

    private String name;

    private String phone;

    private LocalDateTime createDate;
}
