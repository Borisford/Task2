package su.ANV.island.actors;

import lombok.Data;
import lombok.ToString;
import su.ANV.island.data.rawData.FoodData;
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

    public void cycle(Cell cell) {
        if (hanger < maxHanger * hangerLimit) {
            if (eat(cell)) {}
        }
    }

    public boolean eat(Cell cell) {
        Set<String> foodInCell = cell.containCreature(foodData.getFoodSet());
        if (foodInCell.isEmpty()) {
            return false;
        }

        return false;
    }

    private boolean findFood() {
        return false;
    }

    private boolean reproduction() {
        return false;
    }

    private boolean findReproduction() {
        return false;
    }

    private boolean justWalk() {
        return true;
    }


}
