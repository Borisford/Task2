package su.ANV.island.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Value;
import su.ANV.island.data.rawData.AnimalData;
import su.ANV.island.data.rawData.CreatureData;
import su.ANV.island.data.rawData.PlantData;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.io.Out;
import su.ANV.island.io.TextOut;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Value
public class Zoo {
    //private static Zoo zoo = null;
    @JsonProperty
    public ConcurrentHashMap<String, AnimalData> animalDataMap;
    @JsonProperty
    public ConcurrentHashMap<String, PlantData> plantDataMap;

    public Zoo() {
        animalDataMap = new ConcurrentHashMap<String, AnimalData>();
        plantDataMap = new ConcurrentHashMap<String, PlantData>();
        //readZooJson();
        /*AnimalData wolf = new AnimalData();
        wolf.setWeight(50);
        wolf.setMaxOnCell(1);
        wolf.setSpeed(3);
        wolf.setMaxHanger(8);
        animalDataMap.put("wolf", wolf);
        AnimalData rabbit = new AnimalData();
        rabbit.setWeight(2);
        rabbit.setMaxOnCell(4);
        rabbit.setSpeed(2);
        rabbit.setMaxHanger(0.45);
        animalDataMap.put("rabbit", rabbit);
        PlantData plantData = new PlantData();
        plantData.setWeight(1);
        plantData.setMaxOnCell(20);
        plantDataMap.put("plant", plantData);*/
        //TextOut.getTextOut().writeln(zoo.toString(), 10);
    }

    public Map<String, CreatureData> creatureDataMap() {
        Map<String, CreatureData> res = new ConcurrentHashMap<String, CreatureData>(animalDataMap);
        res.putAll(plantDataMap);
        return res;
    }

    public CreatureData getDataByName(String creatureName) throws UnknownCreatureException {
        if (animalDataMap.containsKey(creatureName)) {
            return animalDataMap.get(creatureName);
        }
        if (plantDataMap.containsKey(creatureName)) {
            return plantDataMap.get(creatureName);
        }
        throw new UnknownCreatureException("No data for" + creatureName);
    }
}
