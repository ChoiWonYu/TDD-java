package chap07.user_register;

import chap07.user_register.exception.DupIdException;
import chap07.user_register.exception.WeakPasswordException;
import chap07.user_register.type.EmailNotifier;
import chap07.user_register.type.WeakPasswordChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserRegisterTest {

    private UserRegister userRegister;
    private MemoryUserRepository userRepository=new MemoryUserRepository();
    @Mock
    private WeakPasswordChecker mockPasswordChecker;
    @Mock
    private EmailNotifier mockEmailNotifier;


    @BeforeEach
    void setUp() {
        userRegister=new UserRegister(mockPasswordChecker,userRepository,mockEmailNotifier);
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword() {
        //given
        //모의 객체가 다음과 같이 행동하도록 설정한다.
        BDDMockito.given(mockPasswordChecker.isPasswordWeak("pw")).willReturn(true);

        //then
        //유저를 등록했을 때 예외
        assertThrows(WeakPasswordException.class,()->{
            userRegister.register("id","pw","email");
        });
    }

    @Test
    @DisplayName("회원 가입시 암호 검사 수행함")
    void checkPassword() {
        userRegister.register("id","pw","email");

        BDDMockito.then(mockPasswordChecker)
                .should()
                .isPasswordWeak(BDDMockito.anyString());

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

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito.then(mockEmailNotifier)
                        .should().sendRegisterEmail(captor.capture());

        String realEmail=captor.getValue();

        assertEquals("email@email.com",realEmail);
    }
}