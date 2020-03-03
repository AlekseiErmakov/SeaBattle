package homeworks.homework4.myfunctional;

@FunctionalInterface
public interface Supporter<T extends RuntimeException> {
     T notifi();
}
