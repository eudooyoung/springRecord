package com.multi.restapi.section03.valid;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String code;
    private String description;
    private String detail;
}
