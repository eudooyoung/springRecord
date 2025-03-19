package com.multi.dianno.c_anno.dto;

import org.springframework.stereotype.Component;

@Component
public class Pizza implements Food{
    @Override
    public void eat(String foodName) {
        System.out.println(foodName + "피자를  먹는중 !!");
    }
}
