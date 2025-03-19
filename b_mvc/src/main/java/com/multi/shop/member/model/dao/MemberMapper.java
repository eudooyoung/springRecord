package com.multi.shop.member.model.dao;

import com.multi.shop.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    // 메소드명과, 해당 mapper 내 쿼리 id 키값이 일치해야 함
    int insertMember(MemberDTO memberDTO);

    String selectEncryptedPwd(MemberDTO memberDTO);

    MemberDTO selectLoginMember(MemberDTO memberDTO);
}
