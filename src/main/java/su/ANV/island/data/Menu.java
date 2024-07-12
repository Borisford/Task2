package su.ANV.island.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import su.ANV.island.data.rawData.FoodData;
import su.ANV.island.exception.UnknownCreatureException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Value
public class Menu {
    @JsonProperty
    public ConcurrentHashMap<String, FoodData> howToEat;

    public Menu() {
        howToEat = new ConcurrentHashMap<String, FoodData>();
        FoodData mapTmp = new FoodData();
        mapTmp.addFood("rabbit", 60);
        howToEat.put("wolf", mapTmp);
        mapTmp = new FoodData();
        mapTmp.addFood("plant", 100);
        howToEat.put("rabbit", mapTmp);
    }

}
