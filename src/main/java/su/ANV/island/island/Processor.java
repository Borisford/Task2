package su.ANV.island.island;

import su.ANV.island.actors.Animal;
import su.ANV.island.actors.Creature;
import su.ANV.island.actors.Plant;
import su.ANV.island.exception.CellOutOfIslandExceptoin;
import su.ANV.island.exception.NoCreatureException;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.io.TextOut;
import su.ANV.island.params.Params;
import su.ANV.island.services.*;

import java.util.*;


public class Processor {
    private static Processor processor = null;
    private Island island;

    private Processor() {
        island = Island.getIsland();
        TextOut.getTextOut().writeln(island.toString(), 3);
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
                TextOut.write("x = " + x + "; y = " + y + ";");
                processCell(x, y);
                Scanner console = new Scanner(System.in);
                String name = console.nextLine();
            }
        }*/
        int i = 0, x, y;
        Thread[] threads = new Thread[island.getCells().size()];
        CellProcessor cellProcessor;
        for (Cell cell : island.getCells()) {
            x = i % Params.ISLAND_WIDTH;
            y = i / Params.ISLAND_WIDTH;
            TextOut.getTextOut().writeln("(x:" + x + ";y:" + y + ";)", 42);
            cellProcessor = new CellProcessor(cell, x, y);
            threads[i] = new Thread(cellProcessor);
            threads[i].start();
            //processCell(cell, i % Params.ISLAND_WIDTH, i / Params.ISLAND_WIDTH);
            i++;
        }
        i = 0;
        for ( Thread t : threads ) {
            TextOut.getTextOut().writeln(i + "Not Done!", 42);
            try {
                t.join();
            } catch (InterruptedException e) {
                        TextOut.getTextOut().writeln(e.getMessage(), 42);
                TextOut.getTextOut().writeln(e.getStackTrace().toString(), 42);
            }
            TextOut.getTextOut().writeln(i + "Done!", 42);
            i++;
        }
    }



    /*private void processCell(Cell cell, int x, int y) {
        Set<String> creaturesSet = cell.getCreaturesSet();
        List<Creature> creatureList;
        for (String creatureName : creaturesSet) {
            try {
                creatureList = cell.getCreatureList(creatureName);
                for (Creature creature : creatureList) {
                    processCreature(creature, cell, x, y);
                }
            } catch (UnknownCreatureException | NoCreatureException e) {
                TextOut.getTextOut().writeln(e.getMessage(), 1);
                TextOut.getTextOut().writeln(Arrays.toString(e.getStackTrace()), 2);
            }
        }
    }

    private void processCreature(Creature creature, Cell cell, int x, int y) {
        if (creature.isAlive()) {
            if (creature instanceof Animal) {
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
    }*/
}
