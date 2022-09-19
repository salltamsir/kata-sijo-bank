package service;

import dao.IOperationDao;
import model.Operation;

import java.util.List;

public class OperationService implements IOperationService {

    public OperationService(IOperationDao operationDao) {
        this.operationDao = operationDao;
    }

    private IOperationDao operationDao;

    @Override
    public List<Operation> findByAccountId(String accountId) {
        return operationDao.findAll(accountId);
    }
}
