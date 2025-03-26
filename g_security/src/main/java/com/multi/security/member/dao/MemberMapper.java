package com.multi.security.member.dao;

import com.multi.security.member.dto.MemberDTO;

public interface MemberMapper {
    MemberDTO findMemberById(String memberId);

    int registMember(MemberDTO memberDTO);

    MemberDTO findMemberByEmail(String email);

    int registerMember(MemberDTO memberDTO);
}
