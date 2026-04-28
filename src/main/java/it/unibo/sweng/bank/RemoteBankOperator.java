package it.unibo.sweng.bank;

public interface RemoteBankOperator {

    // Perform a transaction with a remote back using the SET protocol
    boolean transfer(String destinationAccountCode, double amount);

}