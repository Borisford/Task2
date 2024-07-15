package su.ANV.island.params;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Params {
    public final static int ISLAND_WIDTH;
    public final static int ISLAND_HEIGHT;
    public final static String ZOO_JSON_PATH;
    public final static String MENU_JSON_PATH;
    public final static int MAX_DAYS;
    public final static int REPRODUCTION_CHANCE;

    static {
        ParamsPattern pp = new ParamsPattern();
        ObjectMapper om = new ObjectMapper();
        File f = new File("src/main/resources/params.json");
        try {
            pp =  om.readValue(f, ParamsPattern.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ISLAND_WIDTH = pp.ISLAND_WIDTH;
        ISLAND_HEIGHT = pp.ISLAND_HEIGHT;
        ZOO_JSON_PATH = pp.ZOO_JSON_PATH;
        MENU_JSON_PATH = pp.MENU_JSON_PATH;
        MAX_DAYS = pp.MAX_DAYS;
        REPRODUCTION_CHANCE = pp.REPRODUCTION_CHANCE;
    }
}
