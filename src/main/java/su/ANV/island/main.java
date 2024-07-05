package su.ANV.island;

import su.ANV.island.io.TextOut;
import su.ANV.island.island.Processor;

public class main {
    public static void main(String[] args) {
        Processor processor = Processor.getProcessor();
        int i = 0;
        while (i < 100) {
            TextOut.write("Day " + i);
            processor.process();
        }
    }
}
