//package com.bloc.JournalApp.service;
//
//import com.bloc.JournalApp.Repository.UserRepository;
//import com.bloc.JournalApp.entity.User;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.Parameter;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.bson.assertions.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class userServiceTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @ParameterizedTest
//    @CsvSource({
//            "Clark",
//            "Bruce",
//            "Rishav",
//            "Rishi"
//    })
//    public void testAdd(String name ){
//        assertNotNull(userRepository.findByUserName(name),"failed for" + name);
//    }
//
//    @Disabled
//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "6,4,10",
//            "3,4,20"
//    })
//    public void test(int a,int b,int expected){
//        assertEquals(expected,a+b);
//    }
//}
