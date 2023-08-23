package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String password) {
        if(password==null||password.isEmpty())return PasswordStrength.INVALID;

        boolean isLengthEnough=password.length()>=8;
        boolean isContainsNum=isContainsNumber(password);
        boolean isContainsUpp=isContainsUpperCase(password);

        if(isLengthEnough&&!isContainsNum&&!isContainsUpp)return PasswordStrength.WEAK;
        if(isContainsNum&&!isLengthEnough&&!isContainsUpp)return PasswordStrength.WEAK;

        if(!isLengthEnough)return PasswordStrength.NORMAL;
        if(!isContainsNumber(password))return PasswordStrength.NORMAL;
        if(!isContainsUpperCase(password))return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }

    private boolean isContainsUpperCase(String password) {
        long upperCaseCount=password.chars().filter(Character::isUpperCase).count();
        return upperCaseCount>0;
    }

    private boolean isContainsNumber(String password){
        long numCount=password.chars().filter(Character::isDigit).count();
        return numCount>0;
    }

}
