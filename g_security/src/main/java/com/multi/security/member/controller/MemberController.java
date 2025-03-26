package com.multi.security.member.controller;

import com.multi.security.member.dto.MemberDTO;
import com.multi.security.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor  // 생성자 선언 돼있음
@Slf4j // 확인
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public void memberLogin() {
    }

    @GetMapping("/register")
    public void memberRegister() {
    }

    @PostMapping("/register")
    public String registerMember(MemberDTO memberDTO) {

        memberService.registerMember(memberDTO);
        return "redirect:/member/login";
    }
}
