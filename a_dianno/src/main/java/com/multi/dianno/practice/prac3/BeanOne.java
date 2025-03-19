package com.multi.dianno.practice.prac3;

import org.springframework.stereotype.Component;

@Component
public class BeanOne implements Beanterface{
    @Override
    public void introduce() {
        System.out.println("안녕하세요 원빈입니다.");
    }
}
