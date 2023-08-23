package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String password) {
        if(password==null||password.isEmpty())return PasswordStrength.INVALID;

        int metCounts = getMetCounts(password);

        if(metCounts<=1)return PasswordStrength.WEAK;
        if(metCounts==2)return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }

    private int getMetCounts(String password) {
        int metCounts=0;
        if(password.length()>=8) metCounts++;
        if(isContainsNumber(password)) metCounts++;
        if(isContainsUpperCase(password)) metCounts++;
        return metCounts;
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
