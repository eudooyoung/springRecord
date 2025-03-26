package com.multi.security.authentication.service;

import com.multi.security.authentication.dto.CustomUser;
import com.multi.security.member.dao.MemberMapper;
import com.multi.security.member.dto.MemberDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberMapper memberMapper;

    public CustomUserDetailService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("username: " + username); // 로그 찍기 확인

        MemberDTO memberDTO = memberMapper.findMemberByEmail(username);

        if (memberDTO == null) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(memberDTO.getMemberRole()));

        return new CustomUser(memberDTO, authorities);
    }

    public Map<String, List<String>> getPermitListMap() {

        Map<String, List<String>> permitListMap = new HashMap<>();
        List<String> adminPermitList = new ArrayList<>(); // 권한별로 접근가능한 url리스트를 담을 리스트를만듬
        List<String> memberPermitList = new ArrayList<>();

        adminPermitList.add("/admin/dashboard");

        memberPermitList.add("/order/register");

        permitListMap.put("adminPermitList", adminPermitList);
        permitListMap.put("memberPermitList", memberPermitList);

        return permitListMap;
    }
}
