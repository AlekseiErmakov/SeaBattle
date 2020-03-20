package homeworks.functional_homework4.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Random;


public class User {

    @Getter
    private  String name;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private Long id;
    public User(String name, String password){
        this.name = name;
        this.password = password;
        id = new Random().nextLong();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.getName()) &&
                password.equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
}
