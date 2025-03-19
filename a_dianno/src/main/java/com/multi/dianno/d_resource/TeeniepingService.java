package com.multi.dianno.d_resource;

import com.multi.dianno.d_resource.dto.Teenieping;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//@Resource는 JDK의 표준 어노테이션으로, 의존성 주입을 위해 사용
//기본적으로 빈의 이름을 기준으로 주입하지만, 타입을 명시할 수도 있음
//스프링은 먼저 빈 이름("heartsping")을 찾고, 그 후 타입(Teenieping.class)이 일치하는 빈을 주입
//빈 이름이 여러 개 있거나 타입이 모호한 상황에서 이 방식으로 더 정확하게 원하는 빈을 주입

@Service
public class TeeniepingService {

//    1. 필드주입
//    @Resource(name = "heartsping")
//    @Resource(name = "heartsping", type = Teenieping.class)
//    @Resource(name = "heartsping", type = Heartsping.class)
//    private Teenieping teenieping;
//
//
//    public void useMagic() {
//        teenieping.performMagic();
//    }

//    2. 생성자 주입 - 에러발생
//    private Teenieping teenieping;
//
//    @Resource("name = heartsping")
//    public TeeniepingService(Teenieping teenieping) {
//        this.teenieping = teenieping;
//    }

    //    3.메소드 주입
    private Teenieping teenieping;

//    @Resource(name = "heartsping")
    @Resource
    @Qualifier("heartsping")
    public void setTeenieping(Teenieping teenieping) {
        this.teenieping = teenieping;
    }

    public void useMagic() {
        teenieping.performMagic();
    }
}
