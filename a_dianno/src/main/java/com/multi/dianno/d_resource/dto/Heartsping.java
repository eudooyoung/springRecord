package com.multi.dianno.d_resource.dto;

import org.springframework.stereotype.Component;

@Component("heartsping")
public class Heartsping implements Teenieping {
    @Override
    public void performMagic() {
        System.out.println("하츄핑이 사랑의 마법을 사용합니다!");
    }
}