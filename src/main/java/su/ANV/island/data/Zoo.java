package su.ANV.island.data;

import lombok.Value;
import su.ANV.island.data.rawData.AnimalData;
import su.ANV.island.data.rawData.CreatureData;
import su.ANV.island.data.rawData.PlantData;
import su.ANV.island.exception.UnknownCreatureException;

import java.util.concurrent.ConcurrentHashMap;

@Value
public class Zoo {
    private static Zoo zoo = null;
    private ConcurrentHashMap<String, CreatureData> creatureDataMap;

    private Zoo() {
        creatureDataMap = new ConcurrentHashMap<String, CreatureData>();
        AnimalData wolf = new AnimalData();
        wolf.setWeight(50);
        wolf.setMaxOnCell(3);
        wolf.setSpeed(3);
        wolf.setMaxHanger(8);
        creatureDataMap.put("wolf", wolf);
        AnimalData rabbit = new AnimalData();
        rabbit.setWeight(2);
        rabbit.setMaxOnCell(1);
        rabbit.setSpeed(2);
        rabbit.setMaxHanger(0.45);
        creatureDataMap.put("rabbit", rabbit);
        PlantData plantData = new PlantData();
        plantData.setWeight(1);
        plantData.setMaxOnCell(2);
        creatureDataMap.put("plant", plantData);
    }

    public static Zoo getZoo() {
        if (zoo == null) {
            zoo = new Zoo();
        }
        return zoo;
    }

    public CreatureData getDataByName(String creatureName) throws UnknownCreatureException {
        if (creatureDataMap.containsKey(creatureName)) {
            return creatureDataMap.get(creatureName);
        }
        throw new UnknownCreatureException("No data for" + creatureName);
    }
}
