package su.ANV.island.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import su.ANV.island.data.rawData.FoodData;

import java.util.concurrent.ConcurrentHashMap;

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
