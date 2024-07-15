package su.ANV.island.params;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Value;

public class ParamsPattern {
    @JsonProperty
    public int ISLAND_WIDTH;
    @JsonProperty
    public int ISLAND_HEIGHT;
    @JsonProperty
    public String ZOO_JSON_PATH;
    @JsonProperty
    public String MENU_JSON_PATH;
    @JsonProperty
    public int MAX_DAYS;
    @JsonProperty
    public int REPRODUCTION_CHANCE;
}
