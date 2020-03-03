package homeworks.homework4.service;

import homeworks.homework4.exception.UserAlreadyExistException;
import homeworks.homework4.exception.UserNotFoundException;
import homeworks.homework4.model.User;
import homeworks.homework4.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void addUser(String name, String password) {
        userRepository.addUser(User::new, name, password,
                () -> new UserAlreadyExistException(name + " " + password));
    }
    public User getUser(String name){
        return userRepository.getUserByName(name);
    }
    public User getUser(String name, String password){
        return userRepository.getUser(User::new,name, password,
                ()-> new UserNotFoundException(name + " " + password));
    }

}
