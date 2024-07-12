package su.ANV.island.island;

import lombok.Getter;
import lombok.ToString;
import su.ANV.island.actors.Animal;
import su.ANV.island.data.Zoo;
import su.ANV.island.data.ZooAdmin;
import su.ANV.island.exception.CellOutOfIslandExceptoin;
import su.ANV.island.exception.NoCreatureException;
import su.ANV.island.exception.TooMatchCreatureException;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.io.TextOut;
import su.ANV.island.params.Params;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ToString
public class Island {
    private static Island island = null;
    private static Zoo zoo;
    @Getter
    private List<Cell> cells;

    private Island() {
        zoo = ZooAdmin.getZoo(Params.ZOO_JSON_PATH);
        TextOut.getTextOut().writeln(zoo.toString(), 3);
        int size = Params.ISLAND_HEIGHT * Params.ISLAND_WIDTH;
        cells = new CopyOnWriteArrayList<Cell>();
        for (int i = 0; i < size; i++) {
            cells.add(new Cell(zoo));
        }
    }

    public static Island getIsland() {
        if (island == null) {
            island = new Island();
        }
        return island;
    }

    public Cell getCell(int x, int y) throws CellOutOfIslandExceptoin {
        if (x < 0 || x >= Params.ISLAND_WIDTH || y < 0 || y >= Params.ISLAND_HEIGHT) {
            throw new CellOutOfIslandExceptoin();
        }
        return cells.get(y * Params.ISLAND_WIDTH + x);
    }

    public void moveAnimal(Animal animal, int fromX, int fromY, int toX, int toY) throws CellOutOfIslandExceptoin, TooMatchCreatureException {
        Cell fromCell , toCell;
        toCell = getCell(toX, toY);
        try {
            fromCell = getCell(fromX, fromY);
        } catch (CellOutOfIslandExceptoin e) {
            TextOut.getTextOut().writeln(e.getMessage(), 1);
            TextOut.getTextOut().writeln(Arrays.toString(e.getStackTrace()), 2);
            return;
        }
        try {
            toCell.addAnimal(animal);
        } catch (UnknownCreatureException e) {
            TextOut.getTextOut().writeln(e.getMessage(), 1);
            TextOut.getTextOut().writeln(Arrays.toString(e.getStackTrace()), 2);
        }

        try {
            fromCell.removeCreature(animal.getName(), animal);
        } catch (NoCreatureException | UnknownCreatureException e) {
            TextOut.getTextOut().writeln(e.getMessage(), 1);
            TextOut.getTextOut().writeln(Arrays.toString(e.getStackTrace()), 2);
            try {
                toCell.removeCreature(animal.getName(), animal);
            } catch (NoCreatureException | UnknownCreatureException ex) {
                TextOut.getTextOut().writeln(e.getMessage(), 1);
                TextOut.getTextOut().writeln(Arrays.toString(e.getStackTrace()), 2);
            }
        }

    }

    public void matrix() {
        String s = "";
        for (int x = 0; x < Params.ISLAND_WIDTH; x++) {
            for (int y = 0; y < Params.ISLAND_HEIGHT; y++) {
                try {
                    s = island.getCell(x,y).miniCell();
                } catch (CellOutOfIslandExceptoin e) {
                    TextOut.getTextOut().writeln(e.getMessage(), 1);
                    TextOut.getTextOut().writeln(Arrays.toString(e.getStackTrace()), 2);
                }
                TextOut.getTextOut().write(s, 3);
            }
            TextOut.getTextOut().writeln("", 3);
        }
    }

}
