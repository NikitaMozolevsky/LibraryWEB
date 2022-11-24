package com.nikita.al_fp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.Null;
import java.time.Duration;
import java.util.List;

class AssertTest {

    @Test
    void test() {
        int a = 2;
        int b = 2;
        String s1 = "some string 1";
        String s2 = "some string 1";

        Assertions.assertTrue(true);
        Assertions.assertFalse(false);

        /*Assertions.assertTimeout(Duration.ofMillis(500), () -> {
            System.out.println("start");
            Thread.sleep(501);
            System.out.println("finish");
        });*/

        /*Assertions.assertTimeoutPreemptively(Duration.ofMillis(500), () -> { *//**Throw exception if ime out*//*
            System.out.println("start");
            Thread.sleep(6000);
            System.out.println("finish");
        });*/

        Assertions.assertThrows(NullPointerException.class, () -> {
            throw new NullPointerException();
        });
        if (2 == 2) { /**For Example*/
            Assertions.fail();
        }

    }
}
