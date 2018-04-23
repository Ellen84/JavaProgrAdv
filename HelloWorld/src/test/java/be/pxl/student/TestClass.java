package be.pxl.student;

import org.junit.*;
/**
 * Unit test for simple App.
 */
public class TestClass
{
    public TestClass() {
        System.out.println("constructor");
    }

    @BeforeClass
    public static void beforeClass(){
        System.out.println("beforeClass");
    }

    @Before
    public void before(){
        System.out.println("before");
    }

    @Test
    public void test1 (){
        System.out.println("test1");
    }

    @Test
    public void test2 (){
        System.out.println("test2");
    }

    @After
    public void after(){
        System.out.println("after");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println ("afterClass");
    }
}

