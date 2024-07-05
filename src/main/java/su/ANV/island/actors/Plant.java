package su.ANV.island.actors;

import lombok.Data;
import lombok.ToString;
import su.ANV.island.exception.TooMatchCreatureException;
import su.ANV.island.exception.UnknownCreatureException;
import su.ANV.island.island.Cell;

@Data
@ToString(callSuper=true)
public class Plant extends Creature {

}
