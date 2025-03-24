package com.multi.restapi.section02.responseentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int no;
    private String id;
    private String pwd;
    private String name;
    private Date enrollDate;
}
