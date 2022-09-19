package service;

import model.Operation;

import java.util.List;

public interface IOperationService {

    List<Operation> findByAccountId(String accountId);
}
