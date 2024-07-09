package su.ANV.island.island;

import su.ANV.island.actors.Animal;
import su.ANV.island.actors.Creature;
import su.ANV.island.actors.Plant;
import su.ANV.island.exception.CellOutOfIslandExceptoin;
import su.ANV.island.exception.NoCreatureException;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.params.Params;
import su.ANV.island.services.*;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


public class Processor {
    private static Processor processor = null;
    private Island island;

    private Processor() {
        island = Island.getIsland();
        System.out.println(island);
        island.matrix();
    }

    public static Processor getProcessor() {
        if (processor == null) {
            processor = new Processor();
        }
        return processor;
    }

    public void process() {
        /*for (int x = 0; x < Params.ISLAND_WIDTH; x++) {
            for (int y = 0; y < Params.ISLAND_HEIGHT; y++) {
                System.out.println("x = " + x + "; y = " + y + ";");
                processCell(x, y);
                Scanner console = new Scanner(System.in);
                String name = console.nextLine();
            }
        }*/
        int i = 0;
        for (Cell cell:island.getCells()) {
            processCell(cell, i % Params.ISLAND_WIDTH, i / Params.ISLAND_WIDTH);
            i++;
        }
    }

    private void processCell(Cell cell, int x, int y) {
        Set<String> creaturesSet = cell.getCreaturesSet();
            List<Creature> creatureList;
            for (String creatureName:creaturesSet) {
                try {
                    creatureList = cell.getCreatureList(creatureName);
                    for (Creature creature:creatureList) {
                        processCreature(creature, cell, x, y);
                    }
                } catch (UnknownCreatureException | NoCreatureException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
    }

    private void processCreature(Creature creature, Cell cell, int x, int y) {
        if (creature.isAlive()) {
            if(creature instanceof Animal) {
                processAnimal((Animal) creature, cell, x, y);
            }
            if (creature instanceof Plant) {
                processPlant((Plant) creature, cell);
            }
        }
        new GrimReaperService().rip(creature, cell);
    }

    private void processPlant(Plant plant, Cell cell) {
        new ReproductionService().reproduction(plant, cell);

    }

    private void processAnimal(Animal animal, Cell cell, int x, int y) {
        new FoodService().eat(animal, cell);
        new ReproductionService().reproduction(animal, cell);
        new TravelService().goTravel(animal, x, y);
        new HangerService().digest(animal);
    }
}
