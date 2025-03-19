package com.multi.dianno.d_resource.dto;

import org.springframework.stereotype.Component;

@Component("chachaping")
public class Chachaping implements Teenieping {
    @Override
    public void performMagic() {
        System.out.println("차차핑이 희망의 마법을 사용합니다!");
    }
}