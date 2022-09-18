package utils;

import model.Account;
import model.Operation;
import model.enums.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TestUtils {

    public static Account createAccount() {
        return new Account("test", BigDecimal.TEN);
    }

    public static Operation createDepositOperation() {
        return new Operation("test", LocalDateTime.now(), OperationType.DEPOSIT, BigDecimal.ZERO, BigDecimal.TEN, BigDecimal.TEN, true);
    }

    public static Operation createWithdrawOperation() {
        return new Operation("test", LocalDateTime.now(), OperationType.WITHDRAW, BigDecimal.ZERO, BigDecimal.TEN, BigDecimal.TEN, true);
    }
}
