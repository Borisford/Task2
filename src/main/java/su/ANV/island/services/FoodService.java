package su.ANV.island.services;

import su.ANV.island.actors.Animal;
import su.ANV.island.actors.Creature;
import su.ANV.island.data.Menu;
import su.ANV.island.data.Zoo;
import su.ANV.island.exception.AlreadyDeadExceptoin;
import su.ANV.island.exception.NoCreatureException;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.io.TextOut;
import su.ANV.island.island.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FoodService {
    private Menu menu = Menu.getMenu();
    private Zoo zoo = Zoo.getZoo();

    public void eat(Animal animal, Cell cell) {
        Set<String> animalsToEat = cell.containCreature(animal.getFoodData().getFoodSet());
        if (animalsToEat.isEmpty()) {
            TextOut.write("Nothing to eat to " + animal.getName() + " #" + animal.getId());
            return;
        }
        int roll = RandomService.roll(animalsToEat.size());
        String foodName = new ArrayList<>(animalsToEat).get(roll);
        List<Creature> foodList;
        try {
            foodList = cell.getCreatureList(foodName);
        } catch (UnknownCreatureException | NoCreatureException e) {
            TextOut.write(animal.getName() + " #" + animal.getId() + " choose illigal food.");
            return;
        }
        roll = RandomService.roll(foodList.size());
        Creature food = foodList.get(roll);
        try {
            food.die();
        } catch (AlreadyDeadExceptoin e) {
            TextOut.write(animal.getName() + " #" + animal.getId() + " choose illigal food.");
            return;
        }
        animal.eat(food.getWeight());
        TextOut.write(animal.getName() + " #" + animal.getId() + " ate " + foodName);
    }
}
