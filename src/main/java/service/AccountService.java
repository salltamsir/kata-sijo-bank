package service;

import dao.IAccountDao;
import dao.IOperationDao;
import model.Operation;

import java.math.BigDecimal;


public class AccountService implements IAccountService {

    private IOperationDao operationDao;
    private IAccountDao accountDao;

    public AccountService (IOperationDao operationDao, IAccountDao accountDao){
        this.accountDao = accountDao;
        this.operationDao = operationDao;
    }


    @Override
    public Operation deposit(String accountId, BigDecimal transactionAmount) {
        return null;
    }

    @Override
    public Operation withdraw(String accountId, BigDecimal transactionAmount) {
        return null;
    }

    @Override
    public BigDecimal balance(String accountId) {
        return null;
    }

}
