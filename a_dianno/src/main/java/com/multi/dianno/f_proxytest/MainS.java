package com.multi.dianno.f_proxytest;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.cglib.proxy.Enhancer;

public class MainS {
    public static void main(String[] args) {
        // JDK 동적 프록시 테스트
        testJdkProxy();

        // CGLIB 프록시 테스트
        testCglibProxy();
    }
//    스프링은 대상 클래스가 인터페이스를 구현하는 경우 JDK 동적 프록시를 기본적으로 사용
//    인터페이스가 없는 클래스에 대해서는 자동으로 CGLIB 프록시가 생성

    //EmailMessageService 는 인터페이스 있음
    private static void testJdkProxy() {
        // ProxyFactory를 사용하여 프록시 생성
        ProxyFactory jdkProxyFactory = new ProxyFactory(new EmailMessageService()); //ProxyFactory를 통해 EmailMessageService 객체를 프록시로 감싸 JDK 동적 프록시를 생성
       // jdkProxyFactory.setProxyTargetClass(true); //를 설정하면 인터페이스가 있더라도 CGLIB 프록시 방식을 강제
        MessageService jdkProxy = (MessageService) jdkProxyFactory.getProxy();
        System.out.println("JDK Proxy Class: " + jdkProxy.getClass().getName());
        System.out.println("Is JDK Proxy: " + (jdkProxy instanceof java.lang.reflect.Proxy)); //jdkProxy가 java.lang.reflect.Proxy 인스턴스인지 확인하여 JDK 동적 프록시로 생성되었는지확인
        jdkProxy.sendMessage("Testing JDK Proxy!");

    }

    //SmsMessageService 는 인터페이스 없음
    private static void testCglibProxy() {
        // ProxyFactory를 사용하여  프록시 생성
        ProxyFactory cglibProxyFactory = new ProxyFactory(new SmsMessageService());//ProxyFactory는 EmailMessageService와 같이 인터페이스를 추가하지 않은 경우 기본적으로 CGLIB 프록시를 생성

        SmsMessageService cglibProxy = (SmsMessageService) cglibProxyFactory.getProxy();
        System.out.println("CGLIB Proxy Class: " + cglibProxy.getClass().getName());
        System.out.println("Is CGLIB Proxy: " + Enhancer.isEnhanced(cglibProxy.getClass()));//CGLIB 프록시가 생성한 객체인지 확인

        cglibProxy.sendMessage("Testing CGLIB Proxy!");

    }

//    스프링에서 상속을 지양

//    DI(의존성 주입)과 충돌할 가능성
//    - 상속보다는 인터페이스를 사용하고, @Autowired 또는 생성자 주입을 활용하는 것이 더 유연한 설계가 될 수 있다.

//    빈 주입 시 상속 관계 문제
//    -스프링 컨테이너에서 같은 타입의 부모와 자식 빈을 함께 등록하면 예외가 발생할 수 있다.
}
