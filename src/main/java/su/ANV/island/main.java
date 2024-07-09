package su.ANV.island;

import su.ANV.island.io.TextOut;
import su.ANV.island.island.Processor;
import su.ANV.island.island.TestProcessor;

public class main {
    public static void main(String[] args) {
        /*TestProcessor testProcessor = TestProcessor.getTest();
        int i = 0;
        while (i < 100) {
            TextOut.write("Day " + i);
            testProcessor.process();
            i++;
        }*/
        Processor processor = Processor.getProcessor();
        int i = 0;
        while (i < 1) {
            TextOut.write("Day " + i);
            processor.process();
            i++;
        }
    }
}
