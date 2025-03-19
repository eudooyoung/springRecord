package com.multi.dianno.c_anno.dto;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("chicken")
@Primary // @Autowired 사용시 여러 빈이 매칭되면 @Primary 로 우선권을 줄 수 있음.
public class Chicken implements Food{

    @Override
    public void eat(String foodName) {
        System.out.println(foodName + "치킨을 먹는중 !!");
    }
}
