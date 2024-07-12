package su.ANV.island.island;

import lombok.ToString;
import su.ANV.island.actors.Animal;
import su.ANV.island.actors.Creature;
import su.ANV.island.actors.CreatureFactory;
import su.ANV.island.data.rawData.CreatureData;
import su.ANV.island.data.Zoo;
import su.ANV.island.exception.NoCreatureException;
import su.ANV.island.exception.TooMatchCreatureException;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.io.TextOut;
import su.ANV.island.services.RandomService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@ToString
public class Cell {
    private Map<String, List<Creature>> creatures;

    @ToString.Exclude
    private static Zoo zoo; // Zoo.getZoo();

    public Cell(Zoo zoo) {
        this.zoo = zoo;
        creatures = new ConcurrentHashMap<String, List<Creature>>();
        Map<String, CreatureData> zooCreatures = zoo.creatureDataMap();
        List<Creature> tmp;
        int number;
        for(String creatureName: zooCreatures.keySet()) {
            try {
                tmp = new CopyOnWriteArrayList<Creature>();
                number = RandomService.roll(zooCreatures.get(creatureName).getMaxOnCell());
                for (int i = 0; i < number; i++) {
                    tmp.add(CreatureFactory.factory(creatureName));
                }
                creatures.put(creatureName, tmp);
            } catch (UnknownCreatureException e) {
                TextOut.getTextOut().writeln(e.getMessage(), 1);
                TextOut.getTextOut().writeln(Arrays.toString(e.getStackTrace()), 2);
            }
        }
    }

    public Set<String> getCreaturesSet() {
        Set<String> res = new HashSet<>();
        int i;
        for (String s: creatures.keySet()) {
            i = 0;
            try {
                i = getNumberOfCreatures(s);
            } catch (UnknownCreatureException e) {
                TextOut.getTextOut().writeln(e.getMessage(), 1);
                TextOut.getTextOut().writeln(Arrays.toString(e.getStackTrace()), 2);
            }
            if (i > 0) {
                res.add(s);
            }
        }
        return res;
    }

    public List<Creature> getCreatureList(String creatureName) throws UnknownCreatureException, NoCreatureException {
        if (containCreature(creatureName)) {
            return creatures.get(creatureName);
        }
        throw new NoCreatureException("No " + creatureName + " in cell");
    }

    public int getNumberOfCreatures(String creatureName) throws UnknownCreatureException {
        if (!creatures.containsKey(creatureName)) {
            throw new UnknownCreatureException("Unknown creature" + creatureName);
        }
        return creatures.get(creatureName).size();
    }

    public void addCreature(String creatureName) throws UnknownCreatureException, TooMatchCreatureException {
        if (getNumberOfCreatures(creatureName) >= zoo.getDataByName(creatureName).getMaxOnCell()) {
            throw new TooMatchCreatureException("Too match " + creatureName + " in one cell to add one more;");
        }
        creatures.get(creatureName).add(CreatureFactory.factory(creatureName));
    }

    public void addAnimal(Animal animal) throws UnknownCreatureException, TooMatchCreatureException {
        String animalName = animal.getName();
        if (getNumberOfCreatures(animalName) >= zoo.getDataByName(animalName).getMaxOnCell()) {
            throw new TooMatchCreatureException("Too match " + animalName + " in one cell to add one more;");
        }
        creatures.get(animalName).add(animal);
    }

    public void removeCreature(String creatureName, int index) throws NoCreatureException, UnknownCreatureException {
        if (!containCreature(creatureName)) {
            throw new NoCreatureException("No " + creatureName + " in cell;");
        }
        creatures.get(creatureName).remove(index);
    }

    public void removeCreature(String creatureName, Creature creature) throws NoCreatureException, UnknownCreatureException {
        if (!containCreature(creatureName)) {
            throw new NoCreatureException("No " + creatureName + " in cell;");
        }
        creatures.get(creatureName).remove(creature);
    }

    public boolean containCreature(String creatureName) throws UnknownCreatureException {
        return getNumberOfCreatures(creatureName) > 0;
    }

    public Set<String> containCreature(Set<String> nameSet) {
        Set<String> res = new LinkedHashSet<>();
        for (String name: nameSet) {
            try {
                if (containCreature(name)) {
                    res.add(name);
                }
            } catch (UnknownCreatureException e) {
                TextOut.getTextOut().writeln(e.getMessage(), 1);
                TextOut.getTextOut().writeln(Arrays.toString(e.getStackTrace()), 2);
            }
        }
        return res;
    }

    public String miniCell() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (String key: creatures.keySet()) {
            try {
                res.append(key.charAt(0)).append(":").append(getNumberOfCreatures(key)).append(";");
            } catch (UnknownCreatureException e) {
                TextOut.getTextOut().writeln(e.getMessage(), 1);
                TextOut.getTextOut().writeln(Arrays.toString(e.getStackTrace()), 2);
            }
        }
        res.append("]");
        return res.toString();
    }
}
