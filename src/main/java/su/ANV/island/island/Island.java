package su.ANV.island.island;

import lombok.ToString;
import su.ANV.island.params.Params;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ToString
public class Island {
    private static Island island = null;
    private List<Cell> cells;

    private Island() {
        int size = Params.ISLAND_HEIGHT * Params.ISLAND_WIDTH;
        cells = new CopyOnWriteArrayList<Cell>();
        for (int i = 0; i < size; i++) {
            cells.add(new Cell());
        }
    }

    public static Island getIsland() {
        if (island == null) {
            island = new Island();
        }
        return island;
    }

    public Cell getCell(int x, int y) {
        return cells.get(y * Params.ISLAND_WIDTH + x);
    }


}
