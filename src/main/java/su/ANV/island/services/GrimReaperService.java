package su.ANV.island.services;

import su.ANV.island.actors.Creature;
import su.ANV.island.exception.NoCreatureException;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.io.TextOut;
import su.ANV.island.island.Cell;

import java.util.Arrays;

public class GrimReaperService {

    public void rip(Creature creature, Cell cell) {
        if (!creature.isAlive()){
            try {
                cell.removeCreature(creature.getName(), creature);
            } catch (NoCreatureException | UnknownCreatureException e) {
                TextOut.getTextOut().writeln(e.getMessage(), 1);
                TextOut.getTextOut().writeln(Arrays.toString(e.getStackTrace()), 2);
            }
            TextOut.getTextOut().writeln(creature.getName() + " is dead", 0);
            TextOut.getTextOut().writeln(creature.getName() + " #" + creature.getId() + " is dead", 3);
        }
    }
}
