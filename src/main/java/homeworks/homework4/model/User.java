package homeworks.homework4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Random;


public class User {

    @Getter
    private  String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private Long id;
    public User(String name, String password){
        id = new Random().nextLong();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
