package su.ANV.island.services;

import su.ANV.island.actors.Creature;
import su.ANV.island.exception.NoCreatureException;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.io.TextOut;
import su.ANV.island.island.Cell;

public class GrimReaperService {

    public void rip(Creature creature, Cell cell) {
        if (!creature.isAlive()){
            try {
                cell.removeCreature(creature.getName(), creature);
            } catch (NoCreatureException | UnknownCreatureException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            TextOut.write(creature.getName() + " #" + creature.getId() + " is dead");
        }
    }
}
