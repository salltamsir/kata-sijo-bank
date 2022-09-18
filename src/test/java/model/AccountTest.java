package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static utils.TestUtils.createAccount;

class AccountTest {

    Account account = createAccount();


    @Test
    void should_deposit_10() {

        //Given

        //When
        account.deposit(BigDecimal.TEN);

        //Then
        Assertions.assertEquals(BigDecimal.valueOf(20), account.getBalance(), "should be 20");
    }

    @Test
    void should_withdraw_10() {

        //Given

        //When
        boolean withdraw = account.withdraw(BigDecimal.TEN);

        //Then
        Assertions.assertEquals(BigDecimal.ZERO, account.getBalance(), "should be 0");
        assertTrue(withdraw);

    }

    @Test
    void should_fail_withdraw_() {

        //Given

        //When
        boolean withdraw = account.withdraw(BigDecimal.valueOf(20));

        //Then
        Assertions.assertEquals(BigDecimal.TEN, account.getBalance(), "should be 10");
        assertFalse(withdraw);

    }

    @Test
    void should_return_10() {

        //Given

        //When
        BigDecimal balance = account.getBalance();

        //Then
        assertEquals(BigDecimal.TEN, balance, "should be 10");

    }
}

