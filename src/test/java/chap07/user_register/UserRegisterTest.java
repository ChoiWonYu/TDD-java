package chap07.user_register;

import chap07.user_register.exception.WeakPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterTest {

    private UserRegister userRegister;
    private StubPasswordChecker stubPasswordChecker = new StubPasswordChecker();


    @BeforeEach
    void setUp() {
        userRegister=new UserRegister(stubPasswordChecker);
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword() {
        stubPasswordChecker.setWeak(true);

        assertThrows(WeakPasswordException.class,()->{
            userRegister.register("id","pw","email");
        });
    }
}