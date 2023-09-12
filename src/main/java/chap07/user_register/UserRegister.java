package chap07.user_register;

import chap07.user_register.exception.WeakPasswordException;
import chap07.user_register.type.WeakPasswordChecker;

public class UserRegister {

    private WeakPasswordChecker passwordChecker;

    public UserRegister(WeakPasswordChecker passwordChecker) {
        this.passwordChecker=passwordChecker;
    }

    public void register(String id, String pw, String email) {
        if(passwordChecker.isPasswordWeak(pw))
        throw new WeakPasswordException();
    }
}
