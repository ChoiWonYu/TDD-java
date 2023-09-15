package chap07.user_register;

import chap07.user_register.exception.DupIdException;
import chap07.user_register.exception.WeakPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserRegisterTest {

    private UserRegister userRegister;
    private MemoryUserRepository userRepository=new MemoryUserRepository();
    @Mock
    private StubPasswordChecker stubPasswordChecker = new StubPasswordChecker();
    @Mock
    private SpyEmailNotifier emailNotifier=new SpyEmailNotifier();


    @BeforeEach
    void setUp() {
        userRegister=new UserRegister(stubPasswordChecker,userRepository,emailNotifier);
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword() {
        //given
        //약한 암호가 주어지면
        BDDMockito.given(stubPasswordChecker.isPasswordWeak("pw")).willReturn(true);

        //then
        //유저를 등록했을 때 예외
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

    @Test
    @DisplayName("가입하면 메일을 전송")
    void sendEmailWhenRegister() {
        userRegister.register("id", "pw", "email@email.com");

        assertEquals(emailNotifier.isCalled(),true);
        assertEquals("email@email.com",emailNotifier.getEmail());
    }
}