package homeworks.seabatle.generator;

import homeworks.seabatle.bean.ships.repository.PlayerShipsRepository;
import homeworks.seabatle.bean.ships.repository.ShipsRepository;

public class AutoGeneratorService  implements Generateble{
    public ShipsRepository generate(){
        return new PlayerShipsRepository();
    }
}
