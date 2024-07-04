package su.ANV.island.actors;

import lombok.Data;
import lombok.ToString;
import su.ANV.island.island.Cell;

import java.util.Random;

@Data
public class Creature {
    @ToString.Exclude
    Random random = new Random();
    String name;
    boolean isAlive = true;
    double weight;
    int id;


}
