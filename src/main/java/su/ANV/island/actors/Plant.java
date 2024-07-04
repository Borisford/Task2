package su.ANV.island.actors;

import lombok.Data;
import lombok.ToString;
import su.ANV.island.exception.TooMatchCreatureException;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.island.Cell;

@Data
@ToString(callSuper=true)
public class Plant extends Creature {

    public void reproduction(Cell cell) {
        int reproductionChance = 50;
        if (random.nextInt(100) < reproductionChance) {
            try {
                cell.addCreature(name);
            } catch (UnknownCreatureException | TooMatchCreatureException e) {

            }
        }
    }
}
