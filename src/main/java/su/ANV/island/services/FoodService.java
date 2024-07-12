package su.ANV.island.services;

import su.ANV.island.actors.Animal;
import su.ANV.island.actors.Creature;
import su.ANV.island.exception.AlreadyDeadExceptoin;
import su.ANV.island.exception.NoCreatureException;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.io.TextOut;
import su.ANV.island.island.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FoodService {

    public void eat(Animal animal, Cell cell) {
        Set<String> animalsToEat = cell.containCreature(animal.getFoodData().foodSet());
        if (animalsToEat.isEmpty()) {
            TextOut.getTextOut().writeln("Nothing to eat to " + animal.getName() + " #" + animal.getId(), 3);
            return;
        }
        int roll = RandomService.roll(animalsToEat.size());
        String foodName = new ArrayList<>(animalsToEat).get(roll);
        List<Creature> foodList;
        try {
            foodList = cell.getCreatureList(foodName);
        } catch (UnknownCreatureException | NoCreatureException e) {
            TextOut.getTextOut().writeln(animal.getName() + " #" + animal.getId() + " choose illigal food.", 3);
            return;
        }
        roll = RandomService.roll(foodList.size());
        Creature food = foodList.get(roll);
        try {
            food.die();
        } catch (AlreadyDeadExceptoin e) {
            TextOut.getTextOut().writeln(animal.getName() + " #" + animal.getId() + " choose illigal food.", 3);
            return;
        }
        animal.eat(food.getWeight());
        TextOut.getTextOut().writeln(animal.getName() + " #" + animal.getId() + " ate " + foodName, 3);
        TextOut.getTextOut().writeln(animal.getName() + " ate " + foodName, 0);
    }
}
