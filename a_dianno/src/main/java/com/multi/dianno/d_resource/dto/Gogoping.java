package com.multi.dianno.d_resource.dto;

import org.springframework.stereotype.Component;

@Component("gogoping")
public class Gogoping implements Teenieping {
    @Override
    public void performMagic() {
        System.out.println("아자핑이 용기의 마법을 사용합니다!");
    }
}