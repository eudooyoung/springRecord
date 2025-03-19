package com.multi.dianno.practice.prac2;

import org.springframework.stereotype.Component;

@Component
public class BeanOne {
//    @Autowired
    public BeanOne() {
        System.out.println("BeanOne 이 생성 되었습니다.");
    }
}
