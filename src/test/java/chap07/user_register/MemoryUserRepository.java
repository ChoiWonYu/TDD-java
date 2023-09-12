package chap07.user_register;

import chap07.user_register.type.UserRepository;

import java.util.HashMap;
import java.util.Map;


public class MemoryUserRepository implements UserRepository {

    private Map<String,User> users = new HashMap<>();

    @Override
    public User findById(String id) {
        return users.get(id);
    }

    public void save(User user) {
        users.put(user.getId(),user);
    }
}
