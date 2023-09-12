package chap07.user_register;

import chap07.user_register.type.WeakPasswordChecker;

public class StubPasswordChecker implements WeakPasswordChecker {

    private boolean weak;

    public void setWeak(boolean weak) {
        this.weak=weak;
    }

    public boolean isPasswordWeak(String password) {
        return weak;
    }
}
