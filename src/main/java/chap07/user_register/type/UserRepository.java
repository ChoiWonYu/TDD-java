package chap07.user_register.type;

import chap07.user_register.User;

public interface UserRepository {
    User findById(String id);

    void save(User user);
}
