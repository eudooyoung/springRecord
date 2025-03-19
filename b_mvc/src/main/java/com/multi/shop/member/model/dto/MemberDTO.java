package com.multi.shop.member.model.dto;

import lombok.*;

import java.time.LocalDateTime;

// 필요한 기능을 어노테이션으로 구현
// getter, setter, toSring 은 @Data 로 한번에 구현 가능
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private long no;
	private String id;
	private String pw;
	private String name;
	private String tel;
	private LocalDateTime createDate;


}
