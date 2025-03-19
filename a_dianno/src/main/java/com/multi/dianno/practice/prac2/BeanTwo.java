package com.multi.dianno.practice.prac2;

import org.springframework.stereotype.Component;

@Component
public class BeanTwo {
//    @Autowired
    public BeanTwo(BeanOne beanOne) {
        System.out.println("BeanTwo 가 생성 되었습니다.");
        System.out.println(beanOne);
    }
}
