package chap07.user_register;

import chap07.user_register.exception.WeakPasswordException;
import chap07.user_register.type.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterTest {

    private UserRegister userRegister;
    private UserRepository userRepository=new MemoryUserRepository();
    private StubPasswordChecker stubPasswordChecker = new StubPasswordChecker();


    @BeforeEach
    void setUp() {
        userRegister=new UserRegister(stubPasswordChecker,userRepository);
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