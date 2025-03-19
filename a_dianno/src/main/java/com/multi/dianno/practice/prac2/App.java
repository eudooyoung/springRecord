package com.multi.dianno.practice.prac2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.multi.dianno.practice.prac2")
public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        BeanThree beanThree = context.getBean(BeanThree.class);

        beanThree.doubleIntoroduce();
        context.close();

    }
}
