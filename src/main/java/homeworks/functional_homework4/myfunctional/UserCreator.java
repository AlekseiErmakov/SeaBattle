package homeworks.functional_homework4.myfunctional;

import homeworks.functional_homework4.model.User;

@FunctionalInterface
public interface UserCreator<T extends User>{
    T getUser(String name, String password);
}
