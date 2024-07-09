package su.ANV.island.actors;

import lombok.Data;
import lombok.ToString;
import su.ANV.island.data.rawData.FoodData;
import su.ANV.island.exception.AlreadyDeadExceptoin;
import su.ANV.island.island.Cell;

import java.util.Set;

@Data
@ToString(callSuper=true)
public class Animal extends Creature {
    double hanger;
    int speed;
    double maxHanger;
    FoodData foodData;
    double hangerLimit = 0.5;

    public void eat(double foodMass) {
        hanger += foodMass;
        System.out.println(name + " food after eat = " + hanger);
        hanger = Math.min(hanger, maxHanger);
    }

    public void digest() {
        hanger -= maxHanger / 10;
        System.out.println(name + " food after digest = " + hanger);
        if (hanger <= 0) {
            try {
                die();
            } catch (AlreadyDeadExceptoin e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
