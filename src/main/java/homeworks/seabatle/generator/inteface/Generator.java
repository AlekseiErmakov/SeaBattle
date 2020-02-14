package homeworks.seabatle.generator.inteface;

import homeworks.seabatle.bean.ships.Ship;

public interface Generator<T> {
    T next();
}
