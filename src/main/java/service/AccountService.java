package service;

import dao.IAccountDao;
import dao.IOperationDao;
import exceptions.AccountNotFoundException;
import exceptions.InvalidAmountException;
import model.Account;
import model.Operation;
import model.enums.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class AccountService implements IAccountService {

    private IOperationDao operationDao;
    private IAccountDao accountDao;

    public AccountService (IOperationDao operationDao, IAccountDao accountDao){
        this.accountDao = accountDao;
        this.operationDao = operationDao;
    }


    @Override
    public Operation deposit(String accountId, BigDecimal transactionAmount) {
        if(transactionAmount.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidAmountException(transactionAmount);
        }
        Account account = accountDao.findById(accountId)
                .orElseThrow(()-> new AccountNotFoundException(accountId));

        BigDecimal previousAmount = account.getBalance();

        account.deposit(transactionAmount);

        Operation operation = new Operation(account.getId(), LocalDateTime.now(), OperationType.DEPOSIT, previousAmount, account.getBalance(), transactionAmount, true);

        accountDao.update(account);
        operationDao.save(operation);

        return operation;

    }

    @Override
    public Operation withdraw(String accountId, BigDecimal transactionAmount) {

        if(transactionAmount.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidAmountException(transactionAmount);
        }

        Account account = accountDao.findById(accountId)
                .orElseThrow(()-> new AccountNotFoundException(accountId));

        BigDecimal previousAmount = account.getBalance();

        boolean isSuccess = account.withdraw(transactionAmount);
        Operation operation = new Operation(account.getId(), LocalDateTime.now(), OperationType.WITHDRAW, previousAmount, account.getBalance(), transactionAmount, isSuccess);
        if (isSuccess) {
            accountDao.update(account);
        }
        operationDao.save(operation);


        return operation;
    }

    @Override
    public BigDecimal balance(String accountId) {
        Account account = accountDao.findById(accountId)
                .orElseThrow(()-> new AccountNotFoundException(accountId));

        return account.getBalance();
    }

}
