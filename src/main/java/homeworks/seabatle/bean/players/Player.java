package homeworks.seabatle.bean.players;

import homeworks.seabatle.bean.field.Field;

import lombok.Data;

@Data
public abstract class Player  {
    private Field field;
    private String name;

}
