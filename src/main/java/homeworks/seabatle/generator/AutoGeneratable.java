package homeworks.seabatle.generator;

import homeworks.seabatle.bean.ships.repository.ShipsRepository;

public interface AutoGeneratable extends Generateble{
    @Override
    default ShipsRepository generate(){
        return autoGenerate();
    }
    ShipsRepository autoGenerate();
}
