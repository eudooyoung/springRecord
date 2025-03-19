package com.multi.shop.common.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LoginFilter init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        if (httpRequest.getSession().getAttribute("loginMember") == null) {
            log.info("LoginFilter doFilter Session loginMember == null");
            servletRequest.setAttribute("message", "로그인한 사용자만 제품을 볼 수 있습니다.");

            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("/WEB-INF/views/common/failed.jsp");
            dispatcher.forward(servletRequest, servletResponse);

            log.info("LoginFilter forwarding errorpage");

            return; // 더이상 요청을 진행하지 않음
        }

        // 로그인 정보가 있을 경우 다음 필터나 서블릿으로 요청 전달
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("LoginFilter doFilter next filter, servlet");
    }

    @Override
    public void destroy() {
        log.info("LoginFilter destory");
        Filter.super.destroy();
    }
}
