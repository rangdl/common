package com.example.common.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.function.Consumer;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonWebApplicationTests {

    @Test
    public void contextLoads() {
        String empty =null;
        Consumer consumer = System.out::println;
        Optional<String> str = Optional.ofNullable("a");
        Optional<String> str2 = Optional.ofNullable(empty);
        String s = Optional.ofNullable("aa").orElse("bb");
        String bb = Optional.ofNullable(empty).orElse("bb");


        consumer.accept(str.orElse("b"));
        consumer.accept(str2.orElse("b"));
        consumer.accept(s);
        consumer.accept(bb);
    }
}
