package com.hhstu.shelldemo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *  日志门面：SLF4j
 *
 *  日志实现：Logback
 *
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
//@Data
public class LoggerTest {

   // private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1(){
        //输出变量
        String name = "张三";
        String password = "124789";

        /*logger.debug("debug...............");
        logger.info("info..............");
        logger.error("error.................");*/
        log.debug("debug......");
        log.info("info....");
        log.info("name={},password={}",name,password);
        log.error("error....");
    }

}
