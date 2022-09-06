package homework7;

import homework7.Annotation.AfterSuite;
import homework7.Annotation.BeforeSuite;
import homework7.Annotation.Test;

public class Dog {

    private String name;
    private int age;


    @BeforeSuite
    private void testBeforeSuite() {
        System.out.println("BeforeSuite");
    }

    @AfterSuite
    private void testAfterSuite() {
        System.out.println("AfterSuite");
    }

    @Test(priority = 2)
    private void test2() {
        System.out.println("2");
    }

    @Test(priority = 8)
    private void test8() {
        System.out.println("8");
    }

    @Test()
    private void test1() {
        System.out.println("1");
    }

    @Test()
    private void test12() {
        System.out.println("1.2");
    }

    @Test()
    private void test11() {
        System.out.println("1.1");
    }

    @Test(priority = 3)
    private void test3() {
        System.out.println("3");
    }

    @Test(priority = 4)
    private void test4() {
        System.out.println("4");
    }

    @Test(priority = 5)
    private void test5() {
        System.out.println("5");
    }


}
