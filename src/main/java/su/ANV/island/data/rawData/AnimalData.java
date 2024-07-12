package su.ANV.island.data.rawData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class AnimalData extends CreatureData {
    @JsonProperty
    int speed;
    @JsonProperty
    double maxHanger;
}
