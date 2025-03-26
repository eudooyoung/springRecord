package com.multi.security.member.service;

import com.multi.security.member.dao.MemberMapper;
import com.multi.security.member.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j // 로그
public class MemberService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final MemberMapper memberMapper;

    public MemberService(BCryptPasswordEncoder passwordEncoder, MemberMapper memberMapper) {
        this.passwordEncoder = passwordEncoder;
        this.memberMapper = memberMapper;
    }

    @Transactional
    public void registerMember(MemberDTO memberDTO) {

        String encodePwd = passwordEncoder.encode(memberDTO.getPw());
        memberDTO.setPw(encodePwd);

        int result = memberMapper.registerMember(memberDTO);
        if(result <= 0) {
            throw new RuntimeException("registerMember failed: count = 0");
        }

    }
}
