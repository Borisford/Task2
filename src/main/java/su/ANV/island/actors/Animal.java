package su.ANV.island.actors;

import lombok.Data;
import lombok.ToString;
import su.ANV.island.data.rawData.FoodData;

@Data
@ToString(callSuper=true)
public class Animal extends Creature {
    double hanger;
    int speed;
    double maxHanger;
    FoodData foodData;
}
