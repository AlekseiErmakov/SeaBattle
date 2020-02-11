package homeworks.seabatle.bean.players;

import homeworks.seabatle.bean.ships.repository.ShipsRepository;
import homeworks.seabatle.generator.AutoGeneratable;
import homeworks.seabatle.generator.AutoGeneratorService;
import lombok.Getter;

@Getter
public class Computer extends Player implements AutoGeneratable {
    String name = "Skynet";

    @Override
    public ShipsRepository autoGenerate() {
        AutoGeneratorService autoService = new AutoGeneratorService();
        return autoService.generate();
    }

    @Override
    public void shoot() {

    }
}
