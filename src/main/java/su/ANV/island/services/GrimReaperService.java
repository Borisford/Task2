package su.ANV.island.services;

import su.ANV.island.actors.Creature;
import su.ANV.island.exception.NoCreatureException;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.io.TextOut;
import su.ANV.island.island.Cell;

public class GrimReaperService {

    public void rip(Creature creature, Cell cell) {
        try {
            cell.removeCreature(creature.getName(), creature);
        } catch (NoCreatureException | UnknownCreatureException e) {

        }
        TextOut.write(creature.getName() + " is dead");
    }
}
