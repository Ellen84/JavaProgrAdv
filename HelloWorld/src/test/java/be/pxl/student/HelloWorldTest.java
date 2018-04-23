package be.pxl.student;
import org.junit.*;
import static org.junit.Assert.*;

public class HelloWorldTest {
    @Test
    public void testSayHello(){
        HelloWorld hello = new HelloWorld();
        String answer = hello.sayHello();
        assertEquals ("Hello World", answer);

    }
}
