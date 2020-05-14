import org.junit.Test;

import java.time.ZonedDateTime;

public class TestCase {
    @Test
    public void testTimeZone(){
        ZonedDateTime zbj = ZonedDateTime.now();
        System.out.println(zbj);
    }
}
