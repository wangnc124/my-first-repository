package com.springboot.demo1.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="demo")
@PropertySource("classpath:/application1.properties")
public class Author
{
    private String name1;

    private String age1;

    public String getName() {
        System.out.println("=====" + name1);
        return name1;
    }

    public void setName(String name) {
        this.name1 = name;
    }

    public String getAge() {
        return age1;
    }

    public void setAge(String age) {
        this.age1 = age;
    }
}
