package homeworks.homework4.myfunctional;

import homeworks.homework4.model.User;

@FunctionalInterface
public interface UserCreator<T extends User>{
    T getUser(String name, String password);
}
