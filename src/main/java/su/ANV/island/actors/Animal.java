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
        hanger = Math.min(hanger, maxHanger);
    }

    public void digest() {
        hanger -= maxHanger / 4;
        if (hanger <= 0) {
            try {
                die();
            } catch (AlreadyDeadExceptoin e) {

            }
        }
    }
}
