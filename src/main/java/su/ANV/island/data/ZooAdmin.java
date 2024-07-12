package su.ANV.island.data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ZooAdmin {
    private static Zoo zoo = null;

     private static Zoo readZooJson(String fileName) {
        File f = new File(fileName);
        ObjectMapper om = new ObjectMapper();
        try {
            return om.readValue(f, Zoo.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Zoo getZoo(String fileName) {
        if (zoo == null) {
            zoo = readZooJson(fileName);
        }
        return zoo;
    }
}
