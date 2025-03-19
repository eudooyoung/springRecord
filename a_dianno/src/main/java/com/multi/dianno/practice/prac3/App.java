package com.multi.dianno.practice.prac3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.multi.dianno.practice.prac3")
public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        BeanUser user = context.getBean(BeanUser.class);

         user.getMyBean().introduce();

        System.out.println(user);
    }
}
