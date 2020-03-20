package homeworks.functional_homework4.repository;

import homeworks.functional_homework4.exception.UserNotFoundException;
import homeworks.functional_homework4.model.User;
import homeworks.functional_homework4.myfunctional.Supporter;
import homeworks.functional_homework4.myfunctional.UserCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserRepository {
    @Getter
    private final List<User> users;
    public void addUser(UserCreator<User> creator ,String name, String password, Supporter supporter){
            User user = creator.getUser(name,password);
            if (users.contains(user)){
                throw supporter.notifi();
            } else {
                users.add(user);
            }
    }
    public User getUser(UserCreator<User> creator, String name, String password, Supporter supporter){
        User user = creator.getUser(name,password);
        for (User inRepUser : users){
            if (inRepUser.equals(user)){
                return user;
            }
        }
        throw supporter.notifi();
    }

    public User getUserByName(String name) {
        for (User user : users){
            if (name.equals(user.getName())){
                return user;
            }
        }
        throw new UserNotFoundException(name);
    }

}
