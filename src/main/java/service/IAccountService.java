package service;

import model.Operation;

import java.math.BigDecimal;

public interface IAccountService {

    /** Method used to make a deposit
     *
     * @param id
     * @param amount
     * @return
     */
    Operation deposit(String id, BigDecimal amount);

    /** Return the balance for the account with given id
     *
     * @param id
     * @return
     */
    BigDecimal balance(String id);

    /** Method used to make a withdraw
     *
     * @param id
     * @param amount
     * @return
     */
    Operation withdraw(String id, BigDecimal amount);
}
