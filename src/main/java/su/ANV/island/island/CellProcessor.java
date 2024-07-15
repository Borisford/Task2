package su.ANV.island.island;

import su.ANV.island.actors.Animal;
import su.ANV.island.actors.Creature;
import su.ANV.island.actors.Plant;
import su.ANV.island.exception.NoCreatureException;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.io.TextOut;
import su.ANV.island.services.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CellProcessor implements Runnable {
    private final Cell cell;
    private final int x;
    private final int y;

    public CellProcessor(Cell cell, int x, int y) {
        this.cell = cell;
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        Set<String> creaturesSet = cell.getCreaturesSet();
        List<Creature> creatureList;
        for (String creatureName : creaturesSet) {
            try {
                creatureList = cell.getCreatureList(creatureName);
                for (Creature creature : creatureList) {
                    processCreature(creature);
                }
            } catch (UnknownCreatureException | NoCreatureException e) {
                TextOut.getTextOut().writeln(e.getMessage(), 1);
                TextOut.getTextOut().writeln(Arrays.toString(e.getStackTrace()), 2);
            }
        }
        TextOut.getTextOut().writeln("cell(x:" + x + ";y:" + y + ";) done", 42);
    }

    private void processCreature(Creature creature) {
        if (creature.isAlive()) {
            if (creature instanceof Animal) {
                processAnimal((Animal) creature);
            }
            if (creature instanceof Plant) {
                processPlant((Plant) creature);
            }
        }
        new GrimReaperService().rip(creature, cell);
    }

    private void processPlant(Plant plant) {
        new ReproductionService().reproduction(plant, cell);

    }

    private void processAnimal(Animal animal) {
        new FoodService().eat(animal, cell);
        new ReproductionService().reproduction(animal, cell);
        new TravelService().goTravel(animal, x, y);
        new HangerService().digest(animal);
    }
}
