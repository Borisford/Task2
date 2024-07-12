package su.ANV.island.data.rawData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import su.ANV.island.exception.UnknownCreatureException;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ToString
public class FoodData {
    @JsonProperty
    Map<String, Integer> data = new ConcurrentHashMap<String, Integer>();

    public void addFood(String food, int percent) {
        data.put(food, percent);
    }

    public Set<String> foodSet() {
        return data.keySet();
    }

    public int getPercent(String eaterName) throws UnknownCreatureException {
        if (data.containsKey(eaterName)) {
            return data.get(eaterName);
        }
        throw  new UnknownCreatureException(STR."\{eaterName}eats nothing;");
    }

}
