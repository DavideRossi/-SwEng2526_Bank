package it.unibo.sweng.bank;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class AccountTest {
    @BeforeAll
    static void setTestConfiguration() {
        Configuration.init(true);
    }

    @Test
    void makeTransferWithEnoughAmountShouldSuceed() {
        Account account = Account.builder()
            .setBalance(100)
            .setEmail("noone@nobody.com")
            .setOwner("Mario Bianchi")
            .setPhoneNumber("123456")
            .build();
        assertTrue(account.makeTransfer("123456",50));
    }
}
