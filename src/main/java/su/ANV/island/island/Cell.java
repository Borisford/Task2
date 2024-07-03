package su.ANV.island.island;

import lombok.ToString;
import su.ANV.island.actors.Creature;
import su.ANV.island.actors.CreatureFactory;
import su.ANV.island.data.rawData.CreatureData;
import su.ANV.island.data.Zoo;
import su.ANV.island.exception.NoCreatureException;
import su.ANV.island.exception.TooMatchCreatureException;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.io.TextOut;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@ToString
public class Cell {
    private Map<String, List<Creature>> creatures;
    @ToString.Exclude
    Random random = new Random();
    @ToString.Exclude
    private static final Zoo zoo = Zoo.getZoo();

    public Cell() {
        creatures = new ConcurrentHashMap<String, List<Creature>>();
        Map<String, CreatureData> zooCreatures = zoo.getCreatureDataMap();
        List<Creature> tmp;
        int number;
        for(String creatureName: zooCreatures.keySet()) {
            try {
                tmp = new CopyOnWriteArrayList<Creature>();
                number = random.nextInt(zooCreatures.get(creatureName).getMaxOnCell());
                for (int i = 0; i < number; i++) {
                    tmp.add(CreatureFactory.factory(creatureName));
                }
                creatures.put(creatureName, tmp);
            } catch (UnknownCreatureException e) {
                TextOut.write(e.getMessage());
                TextOut.write(e.getStackTrace().toString());
            }
        }
    }

    public int getNumberOfCreatures(String creatureName) {
        return creatures.get(creatureName).size();
    }

    public void addCreature(String creatureName) throws UnknownCreatureException, TooMatchCreatureException {
        if (getNumberOfCreatures(creatureName) >= zoo.getDataByName(creatureName).getMaxOnCell()) {
            throw new TooMatchCreatureException("Too match " + creatureName + "in one cell to add one more;");
        }
        creatures.get(creatureName).add(CreatureFactory.factory(creatureName));
    }

    public void removeCreature(String creatureName, int index) throws NoCreatureException {
        if (getNumberOfCreatures(creatureName) <= 0) {
            throw new NoCreatureException("No " + creatureName + "in cell;");
        }
        creatures.get(creatureName).remove(index);
    }

    public void removeCreature(String creatureName, Creature creature) throws NoCreatureException {
        if (getNumberOfCreatures(creatureName) <= 0) {
            throw new NoCreatureException("No " + creatureName + "in cell;");
        }
        creatures.get(creatureName).remove(creature);
    }
}
