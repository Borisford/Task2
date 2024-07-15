package su.ANV.island.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import su.ANV.island.data.rawData.AnimalData;
import su.ANV.island.data.rawData.CreatureData;
import su.ANV.island.data.rawData.PlantData;
import su.ANV.island.exception.UnknownCreatureException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Value
public class Zoo {
    @JsonProperty
    public ConcurrentHashMap<String, AnimalData> animalDataMap;
    @JsonProperty
    public ConcurrentHashMap<String, PlantData> plantDataMap;

    public Zoo() {
        animalDataMap = new ConcurrentHashMap<String, AnimalData>();
        plantDataMap = new ConcurrentHashMap<String, PlantData>();
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
