package com.multi.dianno.practice.prac2;

import org.springframework.stereotype.Component;

@Component
public class BeanThree {
    private BeanOne beanOne;
    private BeanTwo beanTwo;
//    @Autowired
    public BeanThree(BeanOne beanOne ,BeanTwo beanTwo) {
        System.out.println("BeanThree 가 생성 되었습니다.");
        this.beanOne = beanOne;
        this.beanTwo = beanTwo;
        System.out.println(beanOne);
        System.out.println(beanTwo);
    }

    public void doubleIntoroduce() {
        System.out.println(beanOne);
        System.out.println(beanTwo);
    }
}
