package homeworks.seabatle.bean.players;

import homeworks.seabatle.bean.ships.repository.ShipsRepository;
import homeworks.seabatle.generator.Generateble;
import homeworks.seabatle.generator.GeneratorService;

public class User extends Player implements Generateble {
    @Override
    public void shoot() {

    }

    @Override
    public ShipsRepository generate() {
        GeneratorService service = new GeneratorService();
        return service.generate();
    }
}
