package su.ANV.island.services;

import su.ANV.island.actors.Creature;
import su.ANV.island.data.Zoo;
import su.ANV.island.exception.TooMatchCreatureException;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.io.TextOut;
import su.ANV.island.island.Cell;

public class ReproductionService {
    private final int reproductionChance = 50;

    public void reproduction(Creature creature, Cell cell) {
        if (RandomService.chance(reproductionChance)) {
            try {
                cell.addCreature(creature.getName());
            } catch (UnknownCreatureException | TooMatchCreatureException e) {

            }
            TextOut.write(creature.getName() + " reprodused itself");
        }
    }
}
