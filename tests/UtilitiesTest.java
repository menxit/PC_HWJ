import Utilities.Utilities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilitiesTest {

    @Test
    void getTrueSomeTime75() {
        double attempts = 1000000;
        double count = 0;
        for(int i = 0; i < attempts; i++) {
            if(Utilities.getTrueSomeTime(.75)) {
                count++;
            }
        }
        assertEquals(.75, count/attempts,0.01);
    }

    @Test
    void getTrueSomeTime50() {
        double attempts = 1000000;
        double count = 0;
        for(int i = 0; i < attempts; i++) {
            if(Utilities.getTrueSomeTime(.5)) {
                count++;
            }
        }
        assertEquals(.5, count/attempts,0.01);
    }

    @Test
    void getTrueSomeTime25() {
        double attempts = 1000000;
        double count = 0;
        for(int i = 0; i < attempts; i++) {
            if(Utilities.getTrueSomeTime(.25)) {
                count++;
            }
        }
        assertEquals(.25, count/attempts,0.01);
    }

    @Test
    void getTrueSomeTime100() {
        double attempts = 1000000;
        double count = 0;
        for(int i = 0; i < attempts; i++) {
            if(Utilities.getTrueSomeTime(1D)) {
                count++;
            }
        }
        assertEquals(1, count/attempts);
    }

}
