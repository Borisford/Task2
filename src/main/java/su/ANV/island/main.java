package su.ANV.island;

import su.ANV.island.io.Out;
import su.ANV.island.io.TextOut;
import su.ANV.island.island.Processor;
import su.ANV.island.params.Params;

public class main {
    public static void main(String[] args) {
        Out.addLevel(0);
        //Out.addLevel(1);
        //Out.addLevel(2);
        //Out.addLevel(3);
        Processor processor = Processor.getProcessor();
        processor.process();
    }
}
