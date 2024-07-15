package su.ANV.island.services;

import su.ANV.island.data.Zoo;
import su.ANV.island.data.ZooAdmin;
import su.ANV.island.data.rawData.CreatureData;
import su.ANV.island.data.rawData.PlantData;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.io.TextOut;
import su.ANV.island.island.Cell;
import su.ANV.island.params.Params;

import java.util.List;

public class ForesterService {
    public boolean checkWoods(List<Cell> cells) {
        boolean res = false;
        boolean stop = false;
        Zoo zoo = ZooAdmin.getZoo(Params.ZOO_JSON_PATH);
        for (Cell cell:cells) {
            for (String creatureName:cell.getCreaturesSet()) {
                try {
                    CreatureData creatureData = zoo.getDataByName(creatureName);
                    if (creatureData instanceof PlantData) {
                        res = true;
                        stop = true;
                        break;
                    }
                } catch (UnknownCreatureException e) {
                    TextOut.getTextOut().writeln(e.getMessage(), 1);
                    TextOut.getTextOut().writeln(e.getStackTrace().toString(), 2);
                }
            }
            if (stop) {
                break;
            }
        }
        return res;
    }
}
