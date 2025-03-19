package com.multi.dianno.c_anno;

import com.multi.dianno.c_anno.dto.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.multi.dianno.c_anno")
public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        Person p1 = context.getBean(Person.class);
        p1.getMyFood().eat("bhc");
        System.out.println(p1);
    }
}
