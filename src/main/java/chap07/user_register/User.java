package chap07.user_register;

public class User {
    String id;
    String password;
    String email;

    public User(String id, String password, String email) {
        this.id=id;
        this.password=password;
        this.email=email;
    }

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }


}
