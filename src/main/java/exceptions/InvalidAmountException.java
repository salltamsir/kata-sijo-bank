package exceptions;

import java.math.BigDecimal;

public class InvalidAmountException extends RuntimeException {

    public InvalidAmountException (BigDecimal transactionAmount){
        super("Amount should be greater than 0 but is ["+transactionAmount+"]");
    }
}
