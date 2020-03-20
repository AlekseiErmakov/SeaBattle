package homeworks.functional_homework4.service;

import homeworks.functional_homework4.exception.UserAlreadyExistException;
import homeworks.functional_homework4.exception.UserNotFoundException;
import homeworks.functional_homework4.model.User;
import homeworks.functional_homework4.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService service;
    @Before
    public void setUp(){
        service = new UserService(new UserRepository(new ArrayList<>()));
    }
    @Test
    public void testAddUser() {
        String alex = "Alex";
        String password1 = "qwerty";

        String fedor = "Fedor";
        String password2 = "12345";

        service.addUser(alex,password1);

        try {
            service.addUser(alex,password1);
        } catch (UserAlreadyExistException ex){
            assertEquals(alex + " " + password1, ex.getMessage());
        }
        service.addUser(fedor,password2);
        service.addUser(alex,password2);
        assertEquals(fedor,service.getUser(fedor).getName());
        User user = service.getUser(alex,password1);
        assertEquals(user,new User(alex,password1));

    }
    @Test(expected = UserAlreadyExistException.class)
    public void testExistEx(){
        String alex = "Alex";
        String password1 = "qwerty";
        service.addUser(alex,password1);
        service.addUser(alex,password1);
    }
    @Test(expected = UserNotFoundException.class)
    public void testNotFoundEx(){
        String alex = "Alex";
        String password1 = "qwerty";

        String fedor = "Fedor";
        String password2 = "12345";
        service.addUser(alex,password1);
        service.getUser(fedor,password2);
    }

}