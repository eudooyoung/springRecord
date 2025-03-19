package com.multi.shop.common.interceptor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// 컨트롤러에 들어가야 Model 등 스프링이 제공해주는 객체를 사용할 수 있음
// 뷰에서 요청 -->filter--> DispatcherServlet -- 인터셉터(preHandle) --> Controller --> Service --> Dao --> DB
//                                <-- 인터셉터 --  Controller <-- Service <-- Dao <-- DB
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override // Controller 실행 전
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginInterceptor: preHandle");

        if (request.getSession().getAttribute("loginMember") == null) {
            log.info("LoginInterceptor: Session loginMember == null");
            request.setAttribute("message", "로그인한 사용자만 제품을 볼 수 있습니다.");
//            response.sendRedirect("/");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/failed.jsp");
            dispatcher.forward(request, response);

            log.info("LoginInterceptor: forwarding errorpage");

            return false; // 더이상 요청을 진행하지 않음
        }

        return true;
    }

    @Override // Controller 실행 후
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        log.info("LoginInterceptor: postHandle");

    }

    @Override // 최종 결과나, 응답이 모두 완료된 후에 DisPatcherServlet 으로 반환되기 전에 수행
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        log.info("LoginInterceptor: afterCompletion");
    }
}
