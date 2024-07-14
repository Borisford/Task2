package su.ANV.island;

import su.ANV.island.io.Out;
import su.ANV.island.io.TextOut;
import su.ANV.island.island.Processor;

public class main {
    public static void main(String[] args) {
        //Out.addLevel(0);
        //Out.addLevel(1);
        //Out.addLevel(2);
        //Out.addLevel(3);
        Out.addLevel(42);
        Processor processor = Processor.getProcessor();
        int i = 0;
        while (i < 3) {
            TextOut.getTextOut().writeln("Day " + i, 42);
            TextOut.getTextOut().writeln("------------------------------------------", 42);
            processor.process();
            i++;
        }
    }
}
