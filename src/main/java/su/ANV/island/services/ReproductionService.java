package su.ANV.island.services;

import su.ANV.island.actors.Creature;
import su.ANV.island.data.Zoo;
import su.ANV.island.exception.TooMatchCreatureException;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.io.TextOut;
import su.ANV.island.island.Cell;

import java.util.Arrays;

public class ReproductionService {
    private final int reproductionChance = 50;

    public void reproduction(Creature creature, Cell cell) {
        if (new RandomService().chance(reproductionChance)) {
            try {
                cell.addCreature(creature.getName());
            } catch (UnknownCreatureException | TooMatchCreatureException e) {
                TextOut.getTextOut().writeln(e.getMessage(), 1);
                TextOut.getTextOut().writeln(Arrays.toString(e.getStackTrace()), 2);
            }
            TextOut.getTextOut().writeln(creature.getName() + " #" + creature.getId() + " reprodused itself", 3);
            TextOut.getTextOut().writeln(creature.getName() + " reprodused itself", 0);
        }
    }
}
