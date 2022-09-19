package dao;

import model.Operation;

import java.util.List;

public interface IOperationDao {

    void save(Operation operation);

    List<Operation> findAll(String accountId);
}
