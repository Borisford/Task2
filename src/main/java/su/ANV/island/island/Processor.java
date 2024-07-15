package su.ANV.island.island;

import su.ANV.island.exception.NoMorePlantsException;
import su.ANV.island.io.TextOut;
import su.ANV.island.params.Params;
import su.ANV.island.services.*;


public class Processor {
    private static Processor processor = null;
    private final Island island;

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
        int day = 0;
        while (true) {
            try {
                processDay();
                if (Params.MAX_DAYS > 0 && day >= Params.MAX_DAYS) {
                    TextOut.getTextOut().writeln("Game stoped on day " + day, 0);
                    break;
                }
            } catch (NoMorePlantsException e) {
                TextOut.getTextOut().writeln("No more plants. Everyone will die.", 0);
                break;
            }
            day++;
        }
    }



    public void processDay() throws NoMorePlantsException {
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
        if (new ForesterService().checkWoods(island.getCells())) {
            TextOut.getTextOut().writeln("No more plants", 0);
            throw new NoMorePlantsException();
        }
    }
}
