package com.multi.security.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {


    @RequestMapping("/denied") // 리퀘스트 매핑은 포스트, 겟 알아서 받음
    public void accessDenied() {
    }

    @PostMapping("/login")
    public void loginFailed() {
    }


}
