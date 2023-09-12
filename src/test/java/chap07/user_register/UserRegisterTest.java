package chap07.user_register;

import chap07.user_register.exception.DupIdException;
import chap07.user_register.exception.WeakPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterTest {

    private UserRegister userRegister;
    private MemoryUserRepository userRepository=new MemoryUserRepository();
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

    @Test
    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    void dupIdExists() {
        userRepository.save(new User("id", "pw1", "email@email.com"));

        assertThrows(DupIdException.class, () -> {
            userRegister.register("id", "pw2", "email");
        });
    }

    @Test
    @DisplayName("같은 ID가 없으면 회원가입 성공")
    void successRegister() {
        userRegister.register("id", "pw", "email");

        User savedUser=userRepository.findById("id");

        assertEquals("id",savedUser.getId());
        assertEquals("email",savedUser.getEmail());
    }
}