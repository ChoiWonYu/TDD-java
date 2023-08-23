package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    private final PasswordStrengthMeter meter=new PasswordStrengthMeter();

    private void assertStrength(String password,PasswordStrength expStr){
        assertEquals(expStr,meter.meter(password));
    }
    @Test
    @DisplayName("모든 규칙을 충족하는 경우")
    public void meetsAllCriteria_Then_String() throws Exception {
        //given
        String password = "ab12!@AB";

        //then
        assertStrength(password,PasswordStrength.STRONG);
    }

    @Test
    @DisplayName("길이만 8글자 미만이고 나머지 조건은 충족하는 경우")
    public void meetsOtherCritia_except_for_Length_then_Normal() throws Exception {
        //given
        String password = "ab12!@A";

        //then
        assertStrength(password,PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("숫자를 포함하지 않고 나머지 조건은 충족하는 경우")
    public void meetsOtherCritia_except_for_number_then_Normal() throws Exception {
        //given
        String password = "ab!@ABqwer";

        //then
        assertStrength(password,PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("값이 없는 경우")
    public void nullput_Then_Invalid() throws Exception {
      //when
       assertStrength(null,PasswordStrength.INVALID);
       assertStrength("",PasswordStrength.INVALID);
    }
    
    @Test
    @DisplayName("대문자를 포함하지 않고 나머지 조건을 충족하는 경우")
    public void meetsOtherCritia_except_for_UpperCase_then_Normal() throws Exception {
      //when
        assertStrength("abc!12345",PasswordStrength.NORMAL);
     }

     @Test
     @DisplayName("길이가 8글자 이상인 조건만 충족하는 경우")
     public void PasswordStrengthMeterTest() throws Exception {
       //when
        assertStrength("aaaaaaaaa",PasswordStrength.WEAK);
      }
}
