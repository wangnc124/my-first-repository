package com.springboot.demo1;

import com.springboot.demo1.bean.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    @Value("${demo.name}")
    private String name;

    @Value("${demo.age}")
    private String age;

    @RequestMapping("/")
    String index() {
        return "hello boot!" + " name=" + name + ", age=" + age;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
