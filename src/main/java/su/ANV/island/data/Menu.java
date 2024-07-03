package su.ANV.island.data;

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
    private static Menu menu = null;
    private ConcurrentHashMap<String, FoodData> howToEat;

    public Menu() {
        howToEat = new ConcurrentHashMap<String, FoodData>();
        FoodData mapTmp = new FoodData();
        mapTmp.addFood("rabbit", 60);
        howToEat.put("wolf", mapTmp);
        mapTmp = new FoodData();
        mapTmp.addFood("plant", 100);
        howToEat.put("rabbit", mapTmp);
    }

    public static Menu getMenu() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }
}
