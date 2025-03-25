package com.multi.restproduct.member.dao;

import com.multi.restproduct.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {
    Optional<MemberDto> findByMemberId(String memberId);
}
