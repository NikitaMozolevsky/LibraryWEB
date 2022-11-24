package com.nikita.al_fp.dao.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static org.junit.jupiter.api.Assertions.*;

@Suite
@SelectPackages("com.howtodoinjava.junit5.examples")
public class PersonDaoImplTest {

    @Test
    void insertPerson() {
    }

    @BeforeAll
    static void setup(){
        System.out.println("@BeforeAll executed");
    }

    @BeforeEach
    void setupThis(){
        System.out.println("@BeforeEach executed");
    }

    @Test
    void name() {

    }

    @Tag("DEV")
    @Test
    void testCalcOne()
    {
        /*System.out.println("======TEST ONE EXECUTED=======");
        Assertions.assertEquals( 4 , Calculator.add(2, 2));*/
    }

    @Tag("PROD")
    @Disabled
    @Test
    void testCalcTwo()
    {
        /*System.out.println("======TEST TWO EXECUTED=======");
        Assertions.assertEquals( 6 , Calculator.add(2, 4));*/
    }

    @AfterEach
    void tearThis(){
        System.out.println("@AfterEach executed");
    }

    @AfterAll
    static void tear(){
        System.out.println("@AfterAll executed");
    }
}