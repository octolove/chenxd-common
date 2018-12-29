//package com.cxd.cool.test;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.cxd.cool.Applicaton;
//
//// SpringJUnit支持，由此引入Spring-Test框架支持 --->SpringRunner
//@RunWith(SpringJUnit4ClassRunner.class)
//// 指定SpringBoot工程的Application启动类
//@SpringBootTest(classes = Applicaton.class)
//public class BaseTest {
//
//    @BeforeClass
//    public static void startOne() {
//        System.out.println("----执行一次----");
//    }
//
//    @AfterClass
//    public static void endOne() {
//        System.out.println("----执行一次----");
//    }
//
//    @Before
//    public void start() {
//        System.out.println("----每次执行----");
//    }
//
//    @After
//    public void end() {
//        System.out.println("----每次执行----");
//    }
//}
