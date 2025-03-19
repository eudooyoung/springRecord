package com.multi.shop.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor // 매개 변수가 없는 생성자 어노테이션
@AllArgsConstructor // 매개 변수가 전부 들어간 생성자 어노테이션
public class BoardDTO {

    private long no;
    private int categoryCode;
    private String title;
    private String content;
    private String writer;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private String status;
    private int count;

}
