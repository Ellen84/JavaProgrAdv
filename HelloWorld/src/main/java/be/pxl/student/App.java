package be.pxl.student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Logger logger = LoggerFactory.getLogger(App.class);
        logger.debug("Hello world");
        //logger gebruiken ipv : System.out.println( "Hello World!" );
    }
}
