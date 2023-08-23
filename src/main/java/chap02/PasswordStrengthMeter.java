package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String password) {
        if(password==null||password.isEmpty())return PasswordStrength.INVALID;
        if(password.length()<8){
            return PasswordStrength.NORMAL;
        }
        if(!isContainNumber(password))return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }

    private boolean isContainNumber(String password){
        long numCount=password.chars().filter(Character::isDigit).count();
        return numCount>0;
    }

}
