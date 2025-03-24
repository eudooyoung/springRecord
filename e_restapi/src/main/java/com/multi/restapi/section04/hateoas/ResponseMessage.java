package com.multi.restapi.section04.hateoas;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ResponseMessage {
    private int httpStatus;
    private String message;
    private Map<String, Object> results;
}
