package com.multi.spring.controller;


import com.multi.spring.model.dto.ThymeTestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/thyme")
public class ThymeTestController {

    @GetMapping("/")
    public String test1() {return "thyme/thymeleaf1";}


    @GetMapping("/test2")
    public String test2(Model model){
        List<ThymeTestDto> list = IntStream.rangeClosed(1,9).asLongStream().mapToObj(i ->{
            ThymeTestDto dto  = ThymeTestDto.builder()
                    .id(i)
                    .name("유재석"+  i )
                    .phone("010-9999-123"+ i)
                    .createDate(LocalDateTime.now())
                    .build();
            return dto;
        }).collect(Collectors.toList());

        model.addAttribute("lists",list);

        return "thyme/thymeleaf1";
    }


    @GetMapping({/*"/thymeleaf1",*/"/thymeleaf2"})
    public void test3(Model model){
        List<ThymeTestDto> list = IntStream.rangeClosed(1,9).asLongStream().mapToObj(i ->{
            ThymeTestDto dto  = ThymeTestDto.builder()
                    .id(i)
                    .name("유재석"+  i )
                    .phone("010-9999-123"+ i)
                    .createDate(LocalDateTime.now())
                    .build();
            return dto;
        }).collect(Collectors.toList());

        model.addAttribute("lists",list);
//        model.addAttribute("authInfo" ,list.get(0));

    }

    @GetMapping("/test3")
    public void test3Href(@RequestParam("id") int id, Model model){
        model.addAttribute("id", id);
    }

    @GetMapping({"/thymeleaf3","/thymeleaf5"})
    public void test4(){

    }
}


