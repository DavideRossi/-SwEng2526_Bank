package it.unibo.sweng.bank;

public class FakeNotificator implements Notificator {
    @Override
    public void notify(String email, String message) {
		System.out.println(
            String.format("FAKING FAKING sending email with text: \"%s\" to %s", message, email));
    }
}
