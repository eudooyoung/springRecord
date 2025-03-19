package com.multi.dianno.practice.prac3;

import org.springframework.stereotype.Component;

@Component
public class BeanTwo implements Beanterface{
    @Override
    public void introduce() {
        System.out.println("안녕하세요 투빈입니다.");
    }
}
