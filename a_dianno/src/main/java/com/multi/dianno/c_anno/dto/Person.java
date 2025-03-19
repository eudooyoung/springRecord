package com.multi.dianno.c_anno.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {

//    스프링이 값을 바로 주입
//    값을 갖고 시작하게 만드는 어노테이션
    @Value("주혁")
    private String name;

//    @Autowired: 필드 타입을 기준으로 빈을 찾음
//    @Autowired : 필드, 생성자, 입력파라미터가 여러개인 메소드(@Qualifier는 메소드의 파라미터)에 적용 가능

    @Autowired
//    @Qualifier("pizza") // @Qualifier: 인터페이스 타입의 빈이 2개 이상인 경우 하나의 빈을 지정하는 어노테이션
    private Food myFood;

    public Person() {
    }

    public Person(String name, Food myFood) {
        this.name = name;
        this.myFood = myFood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Food getMyFood() {
        return myFood;
    }

    public void setMyFood(Food myFood) {
        this.myFood = myFood;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", myFood=" + myFood +
                '}';
    }
}
