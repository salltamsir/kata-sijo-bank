package dao;

import model.Account;

import java.util.Optional;

public interface IAccountDao {

    public Optional<Account> findById(String id);

    public void update(Account account);
}
