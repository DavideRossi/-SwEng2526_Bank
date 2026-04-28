package it.unibo.sweng.bank;

public class NotificatorFactory {
    public Notificator create() {
        if (Configuration.getInstance().isTest()) {
            return new FakeNotificator();
        } else {
            return new EmailNotificator();
        }
    }
}
