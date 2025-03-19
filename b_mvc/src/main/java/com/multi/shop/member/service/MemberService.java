package com.multi.shop.member.service;


import com.multi.shop.member.model.dto.MemberDTO;

public interface MemberService {

    int insertMember(MemberDTO dto);

    MemberDTO loginCheck(MemberDTO dto);

}
