package su.ANV.island.services;

import java.util.Random;

public class RandomService {
    private Random random = new Random();

    public boolean chance(int chance) {
        return random.nextInt(100) < chance;
    }

    public  int roll(int max) {
        return random.nextInt(max);
    }
}
