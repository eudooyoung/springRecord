package com.multi.shop.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
//@Slf4j
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class); // 롬북을 사용하지 않을경우

    //* 임의의 문자열 1개를 의미
    //.. 임의의 문자열 0개 이상을 의미
    //*(..) 모든 메서드 의미

    @Pointcut("execution(* com.multi.shop.*.service.*.*(..))")
    public void serviceLayerPointCut() {
    }

    @Before("serviceLayerPointCut()") // @Before(포인트 컷 정의)
    public void logBefore() {
        log.info("메소드 실행 전");
    }

    @After("serviceLayerPointCut()")
    public void logAfter(JoinPoint join) {
        log.info("메소드 실행 후");

        Signature sig = join.getSignature(); // AOP 가 적용된 메소드의 정보를 반환
        log.info("메소드 호출 후 확인: " + sig.getDeclaringTypeName() + ": " + sig.getName());
//        sig.getDeclaringTypeName(): 메소드가 있는 클래스 풀네임
//        sig.getName(): 타겟 객체가 있는 메소드명
    }

    @AfterReturning(value = "serviceLayerPointCut()", returning = "result")
    public void logAfterReturning(JoinPoint join, Object result) {
        log.info("메소드가 정상적으로 수행된 후 반환값: " + result);

        Signature sig = join.getSignature(); // AOP 가 적용된 메소드의 정보를 반환
        log.info("AfterReturning 메소드 호출 후 확인: " + sig.getDeclaringTypeName() + ": " + sig.getName());
    }

    @AfterThrowing(value = "serviceLayerPointCut()", throwing = "ex")
    public void logAfterThrowing(Exception ex) {
        log.error("메소드 실행 중 에러 발생: AfterThrowing" + ex.getMessage());
    }

    // Before 와 After 를 합쳐서 한번에 컨트롤
    @Around("serviceLayerPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("메소드 실행 전: " +joinPoint.getSignature());
        Object proceed = joinPoint.proceed(); // 실제 메소드를 실행하는 전과 후를 나누는 기준
        stopWatch.stop();

        log.info("메소드 실행 후: " +joinPoint.getSignature());
        log.info("메소드 실행 소요시간: " +stopWatch.getTotalTimeMillis());
        return proceed;

    }
}
