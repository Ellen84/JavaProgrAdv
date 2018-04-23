package be.pxl.student;

import org.junit.*;
import static org.junit.Assert.*;

public class TemperatureTest {

    @Test
    public void testConstructor() {
        float degree = 1.2f;
        Temperature temp = new Temperature(degree);
        float test = temp.getValue();
        assertEquals(degree, test, 0.001); //assertEquals met float en double assertEquals(double expected, double actual, double epsilon)
    }

    @Test
    public void testValue(){
        float degree = 3.2f;
        float wrongDegree = 1;
        Temperature temp = new Temperature(wrongDegree);
        temp.setValue(degree);
        assertEquals(degree, temp.getValue(), 0.001);
    }

}