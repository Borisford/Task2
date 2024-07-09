package su.ANV.island.io;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Out levels :
 * 0 - actions, like feeding, reproduction and walking
 * 1 - exception messages
 * 2 - stack traces
 * 3 - debugging information
 * 4-9 - reserved
 * 10+ - other
 * **/

public abstract class Out {
    protected static final Set<Integer> outLevels = new CopyOnWriteArraySet<Integer>();

    public static void addLevel(int level) {
        outLevels.add(level);
    }

    public abstract void write(String msg, int level);

    public abstract void writeln(String msg, int level);
}
