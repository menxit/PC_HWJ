package Utilities;

import java.util.Random;

public class Utilities {

    public static Integer getRandomIntegerNumber(Integer from, Integer to) {
        Random rand = new Random();
        return rand.nextInt(to) + from;
    }

    public static Boolean getTrueSomeTime(Double probability) {
        Random rand = new Random();
        return rand.nextDouble() <= probability;
    }

}
