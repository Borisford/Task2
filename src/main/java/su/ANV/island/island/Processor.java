package su.ANV.island.island;

import su.ANV.island.actors.Animal;
import su.ANV.island.actors.Creature;
import su.ANV.island.actors.Plant;
import su.ANV.island.exception.CellOutOfIslandExceptoin;
import su.ANV.island.exception.NoCreatureException;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.params.Params;

import java.util.List;
import java.util.Random;
import java.util.Set;


public class Processor {
    private final static Random random = new Random();
    private static Processor processor = null;
    private Island island;

    private Processor() {
        island = Island.getIsland();
    }

    private Processor getProcessor() {
        if (processor == null) {
            processor = new Processor();
        }
        return processor;
    }

    public void process() {
        for (int x = 0; x < Params.ISLAND_WIDTH; x++) {
            for (int y = 0; y < Params.ISLAND_HEIGHT; y++) {
                processCell(x, y);
            }
        }
    }

    private void processCell(int x, int y) {
        try {
            Cell cell = island.getCell(x, y);
            Set<String> creaturesSet = cell.getCreaturesSet();
            List<Creature> creatureList;
            for (String creatureName:creaturesSet) {
                try {
                    creatureList = cell.getCreatureList(creatureName);
                    for (Creature creature:creatureList) {
                        processCreature(creature, cell, x, y);
                    }
                } catch (UnknownCreatureException | NoCreatureException e) {

                }
            }
        } catch (CellOutOfIslandExceptoin e) {

        }
    }

    private void processCreature(Creature creature, Cell cell, int x, int y) {
        if (creature.isAlive()) {
            if(creature instanceof Animal) {
                processAnimal((Animal) creature, cell);
            }
            if (creature instanceof Plant) {
                processPlant((Plant) creature, cell);
            }
        }
    }

    private void processPlant(Plant plant, Cell cell) {
        plant.reproduction(cell);
    }

    private void processAnimal(Animal animal, Cell cell) {
        if (animal.getHanger() < animal.getMaxHanger()) {}
    }
}
