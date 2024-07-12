package su.ANV.island.data.rawData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreatureData {
    @JsonProperty
    double weight;
    @JsonProperty
    int maxOnCell;
}
