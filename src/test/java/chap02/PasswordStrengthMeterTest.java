package chap02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals(PasswordStrength.STRONG,result);
     }

}
