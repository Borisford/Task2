package su.ANV.island.actors;

import su.ANV.island.data.Menu;
import su.ANV.island.data.MenuAdmin;
import su.ANV.island.data.ZooAdmin;
import su.ANV.island.data.rawData.AnimalData;
import su.ANV.island.data.rawData.CreatureData;
import su.ANV.island.data.rawData.PlantData;
import su.ANV.island.data.Zoo;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.params.Params;

import java.util.concurrent.atomic.AtomicInteger;

public class CreatureFactory {
    private static final Zoo zoo = ZooAdmin.getZoo(Params.ZOO_JSON_PATH);
    private static final AtomicInteger MAX_ID = new AtomicInteger(0);
    private static final Menu menu = MenuAdmin.getMenu(Params.MENU_JSON_PATH);

    public static Creature factory(String name) throws UnknownCreatureException {
        CreatureData data = zoo.getDataByName(name);
        Creature res = new Creature();
        if (data instanceof PlantData) {
            res = new Plant();
        }
        if (data instanceof AnimalData) {
            res = new Animal();
            ((Animal)res).setSpeed(((AnimalData) data).getSpeed());
            ((Animal)res).setMaxHanger(((AnimalData) data).getMaxHanger());
            ((Animal)res).setHanger(((AnimalData) data).getMaxHanger() / 2);
            ((Animal)res).setFoodData(menu.getHowToEat().get(name));
        }
        res.setName(name);
        res.setId(MAX_ID.getAndAdd(1));
        res.setWeight(data.getWeight());
        return res;
    }
}
