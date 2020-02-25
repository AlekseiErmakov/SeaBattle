package homeworks.seabatle.functional;

import java.util.List;

@FunctionalInterface
public interface AreaCreator {
    List<Integer> create(int target);
}
