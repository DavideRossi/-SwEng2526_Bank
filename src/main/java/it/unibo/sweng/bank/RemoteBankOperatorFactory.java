package it.unibo.sweng.bank;

public class RemoteBankOperatorFactory {
    public RemoteBankOperator create() {
        if (Configuration.getInstance().isTest()) {
            return new FakeRemoteBankOperator();
        } else {
            return new SETRemoteBankOperator();
        }
    }
}
