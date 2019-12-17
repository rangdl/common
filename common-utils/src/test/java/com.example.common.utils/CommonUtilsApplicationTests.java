package com.example.common.utils;

import com.example.common.utils.core.ObjectUtils;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonUtilsApplicationTests implements Serializable{
    Logger logger = LoggerFactory.getLogger(CommonUtilsApplicationTests.class);

    @Test
    public void testObjectUtils(){
        Person p1 = new Person();
        p1.setName("李四");
        Car car = new Car();
         car.setName("自行车");
         p1.setCar(car);

        Person p2 = ObjectUtils.clone(p1);
         p2.getCar().setName("大卡车");
         p1.print();
         p2.print();
    }

    @Test
    public void testConsumer(){
        List<String> list = Arrays.asList("abc","bcdvccc","aboedss","avfa");
        List<Integer> list2 = Arrays.asList(1,4,6,2,5);
        list.stream().forEach(logger::info);
        list2.stream().forEach((x)->logger.info("{}",x));

        boolean abc = list.stream().anyMatch((x) -> x.equals("abc"));
        boolean all = list.stream().allMatch((x) -> x.indexOf("a")>-1);
        logger.info("{}",abc);
        logger.info("{}",all);
        String[] as = list.stream().filter((x) -> x.indexOf("a") > -1).sorted().toArray(String[]::new);
        Arrays.stream(as).forEach(System.out::println);
    }

    @Test
    public void testPredicate(){
        Predicate<Integer> predicate = x -> {
            x = x + 1;
            return x==2;
        };
        Predicate<Integer> and = predicate.and(z -> z - 1 == 0);
        Predicate<Integer> or = predicate.or(z -> z + 1 == 1);

        logger.info("{}",predicate.test(1));
        logger.info("{}",and.test(1));
        logger.info("{}",and.negate().test(1));
        logger.info("{}",or.test(2));

    }
    @Test
    public void contextLoads() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println("输出所有数据:");

        eval(list, n->true);

        System.out.println("输出所有偶数:");
        eval(list, n-> n%2 == 0 );

        System.out.println("输出大于 3 的所有数字:");
        eval(list, n-> n > 3 );
    }


    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for(Integer n: list) {
            if(predicate.test(n)) {
                System.out.println(n + " ");
            }

        }
    }

    @Data
    public class Person implements Serializable{
        private static final long serialVersionUID = 1L;
        private String name;
        private Car car;
        public void print(){
            System.out.println(this.name+" : "+this.car.name);
        }
    }
    @Data
    public class Car implements Serializable {
        private static final long serialVersionUID = 1L;
        private String name;

    }
}
