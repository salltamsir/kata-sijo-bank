package exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException (String accountId){
        super("Account id : "+accountId+" is not found");
    }
}
