package chap07.user_register;

import chap07.user_register.type.EmailNotifier;

public class SpyEmailNotifier implements EmailNotifier {

    private boolean called;
    private String email;

    public boolean isCalled() {
        return called;
    }

    public void setIsSendTrue() {
        this.called=true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    @Override
    public void sendRegisterEmail(String email) {
        setIsSendTrue();
        setEmail(email);
    }
}
