package su.ANV.island.actors;

import lombok.Data;
import lombok.ToString;
import su.ANV.island.data.rawData.FoodData;
import su.ANV.island.exception.AlreadyDeadExceptoin;
import su.ANV.island.io.TextOut;
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
        TextOut.getTextOut().writeln(name + " food after eat = " + hanger, 3);
        hanger = Math.min(hanger, maxHanger);
    }

    public void digest() {
        hanger -= maxHanger / 10;
        TextOut.getTextOut().writeln(name + " food after digest = " + hanger, 3);
        if (hanger <= 0) {
            try {
                die();
            } catch (AlreadyDeadExceptoin e) {
                TextOut.getTextOut().writeln(e.getMessage(), 1);
                TextOut.getTextOut().writeln(e.getStackTrace().toString(), 2);
            }
        }
    }
}
