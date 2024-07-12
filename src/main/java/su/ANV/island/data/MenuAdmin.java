package su.ANV.island.data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class MenuAdmin {
    private static Menu menu = null;

    private static Menu readMenuJson(String fileName) {
        File f = new File(fileName);
        ObjectMapper om = new ObjectMapper();
        try {
            return om.readValue(f, Menu.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Menu getMenu (String fileName) {
        if (menu == null) {
            menu = readMenuJson(fileName);
        }
        return menu;
    }
}
