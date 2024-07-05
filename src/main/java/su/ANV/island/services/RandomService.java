package su.ANV.island.services;

import java.util.Random;

public class RandomService {
    private static Random random = new Random();

    public static boolean chance(int chance) {
        return random.nextInt(100) < chance;
    }

    public static int roll(int max) {
        return random.nextInt(max);
    }
}
