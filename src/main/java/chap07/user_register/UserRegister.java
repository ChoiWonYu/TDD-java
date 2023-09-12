package chap07.user_register;

import chap07.user_register.exception.WeakPasswordException;
import chap07.user_register.type.UserRepository;
import chap07.user_register.type.WeakPasswordChecker;

public class UserRegister {

    private WeakPasswordChecker passwordChecker;

    private UserRepository userRepository;

    public UserRegister(WeakPasswordChecker passwordChecker,UserRepository userRepository) {
        this.passwordChecker=passwordChecker;
        this.userRepository=userRepository;
    }

    public void register(String id, String pw, String email) {
        if(passwordChecker.isPasswordWeak(pw))
        throw new WeakPasswordException();
    }
}
