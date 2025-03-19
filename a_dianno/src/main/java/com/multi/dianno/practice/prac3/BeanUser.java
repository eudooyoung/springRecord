package com.multi.dianno.practice.prac3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BeanUser {

    @Value("사용자")
    private String name;

    @Autowired
    @Qualifier("beanOne")
    private Beanterface myBean;

    public BeanUser() {
    }

    public BeanUser(String name, Beanterface myBean) {
        this.name = name;
        this.myBean = myBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Beanterface getMyBean() {
        return myBean;
    }

    public void setMyBean(Beanterface myBean) {
        this.myBean = myBean;
    }

    @Override
    public String toString() {
        return "BeanUser{" +
                "name='" + name + '\'' +
                ", myBean=" + myBean +
                '}';
    }
}
