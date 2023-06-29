package com.example.hznubooks;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HznubooksApplicationTests {

    @Test
    void hash() {
        System.out.println(String.valueOf("1".hashCode()));
    }

}
