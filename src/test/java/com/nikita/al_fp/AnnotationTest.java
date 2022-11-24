package com.nikita.al_fp;

import org.junit.jupiter.api.*;

class AnnotationTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }

    @BeforeEach
    void before() {
        System.out.println("@BeforeEach");
    }

    @AfterEach
    void after() {
        System.out.println("@AfterEach");
    }

    @Test
    void test() {
        System.out.println("@Test");
    }

    @Test
    void test2() {
        System.out.println("@Test2");
    }

    @DisplayName("Main test")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class InnerClassTest {

        @BeforeAll
        void beforeAll() {
            System.out.println("@BeforeAll inner");
        }

        @BeforeEach
        void before() {
            System.out.println("@BeforeEach inner");
        }

        @AfterEach
        void after() {
            System.out.println("@AfterEach inner");
        }

        @DisplayName("Some operation")
        @Test
        void test() {
            System.out.println("@Test inner");
        }

        @Disabled("Test disable annotation")
        @Test
        void test2() {
            System.out.println("@Test2 inner    ");
        }
    }
}
