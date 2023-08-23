package chap02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    @Test
    @DisplayName("모든 규칙을 충족하는 경우")
    public void meetsAllCriteria_Then_String() throws Exception {
      //given
       String password="ab12!@AB";
        PasswordStrengthMeter meter=new PasswordStrengthMeter();
      //when
        PasswordStrength result=meter.meter(password);
      //then
        assertEquals(PasswordStrength.STRONG,result);
     }

     @Test
     @DisplayName("길이만 8글자 미만이고 나머지 조건은 충족하는 경우")
     public void meetsOtherCritia_except_for_Length_then_Normal() throws Exception {
       //given
        PasswordStrengthMeter meter=new PasswordStrengthMeter();
        String password="ab12!@A";
       //when
       PasswordStrength result=meter.meter(password);
       //then
         assertEquals(result,PasswordStrength.NORMAL);
      }
}
