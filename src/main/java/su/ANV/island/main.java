package su.ANV.island;

import su.ANV.island.io.Out;
import su.ANV.island.io.TextOut;
import su.ANV.island.island.Processor;
import su.ANV.island.params.Params;

public class main {
    public static void main(String[] args) {
        System.out.println(Params.ISLAND_HEIGHT);
        System.out.println(Params.ISLAND_WIDTH);
        System.out.println(Params.ZOO_JSON_PATH);
        System.out.println(Params.MENU_JSON_PATH);
        System.out.println(Params.MAX_DAYS);
        System.out.println(Params.REPRODUCTION_CHANCE);

        Out.addLevel(0);
        //Out.addLevel(1);
        //Out.addLevel(2);
        //Out.addLevel(3);
        Processor processor = Processor.getProcessor();
        processor.process();
    }
}
