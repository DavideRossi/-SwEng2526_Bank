package it.unibo.sweng.bank;

import java.util.Optional;

public class Account {
	private static final int ID_LIMIT = 12345;
	private static final String EMAIL_DEFAULT = "";
	private static final String PHONE_NUMBER_DEFAULT = "";

	public static class AccountBuilder {
		private Optional<String> owner = Optional.empty();
		private Optional<Double> balance = Optional.empty();
		private Optional<Integer> id = Optional.empty();
		private Optional<String> email = Optional.empty();
		private Optional<String> phoneNumber = Optional.empty();

		public static AccountBuilder createBuilder() {
			return new AccountBuilder();
		}
		
		private AccountBuilder() {
		}
		
		public AccountBuilder setOwner(String owner) {
			this.owner = Optional.of(owner);
			return this;
		}

		public AccountBuilder setBalance(double balance) {
			this.balance = Optional.of(balance);
			return this;
		}

		public AccountBuilder setId(int id) {
			this.id = Optional.of(id);
			return this;
		}
		
		public AccountBuilder setEmail(String email) {
			this.email = Optional.of(email);
			return this;
		}
		
		public AccountBuilder setPhoneNumber(String phoneNumber) {
			this.phoneNumber = Optional.of(phoneNumber);
			return this;
		}
		
		public Account build() {
			if(this.owner.isEmpty()) {
				throw new IllegalStateException("Owner must be set");
			}
			String owner = this.owner.get();
			String email = this.email.orElse(EMAIL_DEFAULT);
			String phoneNumber = this.phoneNumber.orElse(PHONE_NUMBER_DEFAULT);
			int id = this.id.orElse((int)(Math.random() * ID_LIMIT));
			double balance = this.balance.orElse(.0);
			return new Account(owner, email, phoneNumber, id, balance);
		}
	}

	private String owner;
	private double balance;
	private int id;
	private String email;
	private String phoneNumber;
	
	private Account(String owner, String email, String phoneNumber, int id, double balance) {
		this.owner = owner;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.id = id;
		this.balance = balance;
	}

	public static AccountBuilder builder() {
		return AccountBuilder.createBuilder();
	}

	public boolean withdraw(double amount) {
		if(this.balance - amount >= 0) {
			this.balance -= amount;
			return true;
		} else {
			return false;
		}
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	@Override
	public String toString() {
		return String.format("{Account id: %d, owner: %s, balance: %f}", this.id, this.owner, this.balance);
	}

	// Transfer from a local account to a remote one
	public boolean makeTransfer(String destinationAccountCode, double amount) {
		NotificatorFactory notificatorFactory = new NotificatorFactory();
		Notificator notificator = notificatorFactory.create();
		RemoteBankOperatorFactory bankOperatorFactory = new RemoteBankOperatorFactory();
		RemoteBankOperator remoteBankOperator = bankOperatorFactory.create();

		if(getBalance() >= amount) {
			if(remoteBankOperator.transfer(destinationAccountCode, amount)) {
				withdraw(amount);
				notificator.notify(
					this.getEmail(),
					String.format("Trasferred %f from %s to %s", 
							amount, this, destinationAccountCode));
				return true;
			} else {
				notificator.notify(
					this.getEmail(),
					String.format("Failed to trasfer %f from %s to %s", 
					amount, this, destinationAccountCode));
				return false;
			}
		} else {
			return false;
		}
	}
}
