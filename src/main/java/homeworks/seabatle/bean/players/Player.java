package homeworks.seabatle.bean.players;

import homeworks.seabatle.bean.field.Field;
import homeworks.seabatle.generator.Generateble;
import lombok.Data;

@Data
public abstract class Player implements Shootable, Generateble {
    private Field field;
    private String name;

}
