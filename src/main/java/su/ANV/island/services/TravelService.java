package su.ANV.island.services;

import su.ANV.island.actors.Animal;
import su.ANV.island.exception.CellOutOfIslandExceptoin;
import su.ANV.island.exception.TooMatchCreatureException;
import su.ANV.island.island.Island;
import su.ANV.island.params.Params;

public class TravelService {
    private Island island = Island.getIsland();

    public void goTravel(Animal animal, int fromX, int fromY) {
        int toX = fromX, toY = fromY;
        int dx, dy, dir;
        boolean isDone = false;
        dx = RandomService.roll(animal.getSpeed());
        dy = animal.getSpeed() - dx;
        while (!isDone){
            try {
                dir = RandomService.roll(4);
                if (dir == 0) {
                    toX += dx;
                    toY += dy;
                } else if (dir == 1) {
                    toX -= dx;
                    toY += dy;
                } else if (dir == 2) {
                    toX += dx;
                    toY -= dy;
                } else if (dir == 3) {
                    toX -= dx;
                    toY -= dy;
                }
                toX = Math.max(toX, 0);
                toX = Math.min(toX, Params.ISLAND_WIDTH);
                toY = Math.max(toY, 0);
                toY = Math.min(toY, Params.ISLAND_HEIGHT);
                island.moveAnimal(animal, fromX, fromY, toX, toY);
                isDone = true;
            } catch (CellOutOfIslandExceptoin | TooMatchCreatureException e) {
                isDone = false;
            }
        }

    }
}
