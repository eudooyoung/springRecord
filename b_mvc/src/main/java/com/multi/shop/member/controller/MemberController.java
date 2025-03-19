package com.multi.shop.member.controller;

import com.multi.shop.member.model.dto.MemberDTO;
import com.multi.shop.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
@SessionAttributes("loginMember")// 세션 생성 어노테이션?
@RequiredArgsConstructor // lombook 생성자 어노테이션
public class MemberController {

    private final MemberService memberService;

    // "WEB-INF/views/common/failed.jsp"로 이동
    // viewresolver에 의해 앞 뒤로 경로와 확장자를 붙여서 클라이언트에 보여줄수있게 처리됨
    @RequestMapping("/common/failed")
    public String showErrorPage() {
        // 프리픽스, 서픽스 추가되어 지정된 파일 열어줌
        return "common/failed";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userId") String memberId,
                        @RequestParam("userPwd") String memberPw, Model model, RedirectAttributes redirectAttributes) {

        MemberDTO requestMember = new MemberDTO();
        requestMember.setId(memberId);
        requestMember.setPw(memberPw);

        try {
            // @SessionAttributes로 세션에 로그인 정보 저장(세션 설정)
            MemberDTO loginMember = memberService.loginCheck(requestMember);

            System.out.println(loginMember);

            if (loginMember != null) {

                model.addAttribute("loginMember", loginMember);

                return "redirect:/";

            } else {

                redirectAttributes.addFlashAttribute("message", "로그인실패");

                return "redirect:/member/common/failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "로그인실패");

            return "redirect:/member/common/failed";

        }

    }

    @GetMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete(); // 현재 SessionAttribute 에 의해서 저장된 객체를 제거

        return "redirect:/";
    }

//    @GetMapping("/insert")
//    public String insertForm() {
//        return "member/insert";
//    }

    // url 과 반환하는 form의 경로가 일치하는 경우는 void 로 메서드를 호출할 수 있다.
    @GetMapping("/insert")
    public void insertForm() {
    }

    @PostMapping("/insert")
    public String insertMember(@ModelAttribute MemberDTO memberDTO, Model model) {
//        try {
//            int result = memberService.insertMember(memberDTO);
//            System.out.println("insertMember" + result);
//            if (result > 0) {
//                model.addAttribute("successCode", "insertMember");
//                return "common/success";
//            } else {
//                model.addAttribute("message", "회원가입실패");
//                return "common/failed";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("message", "회원가입실패");
//            return "common/failed";
//        }
        int result = memberService.insertMember(memberDTO);
        System.out.println("insertMember" + result);
        // 서비스에서 던진 예외처리
        model.addAttribute("successCode", "insertMember");
        return "common/success";
    }
}
