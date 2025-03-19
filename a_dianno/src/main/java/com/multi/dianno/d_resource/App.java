package com.multi.dianno.d_resource;

import com.multi.dianno.d_resource.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        TeeniepingService teeniepingService = context.getBean(TeeniepingService.class);
        teeniepingService.useMagic();
    }
}
