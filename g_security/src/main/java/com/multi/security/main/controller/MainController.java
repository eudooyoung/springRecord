package com.multi.security.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/", "/main"}) // 두 개일 때는 {}안에 넣어줘야 함
    public String main() {
        return "main/main";
    }
}
