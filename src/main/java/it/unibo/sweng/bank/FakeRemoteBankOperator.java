package it.unibo.sweng.bank;

public class FakeRemoteBankOperator implements RemoteBankOperator {
	@Override
	public boolean transfer(String destinationAccountCode, double amount) {
		System.out.println(String.format("FAKING FAKING transferring %f to %s using SET", amount, destinationAccountCode));
		return true;
	}

}
