package service;

import dao.IAccountDao;
import dao.IOperationDao;
import exceptions.AccountNotFoundException;
import exceptions.InvalidAmountException;
import model.Account;
import model.Operation;
import model.enums.OperationStatus;
import model.enums.OperationType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static utils.TestUtils.createAccount;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    IAccountDao accountDao;

    @Mock
    IOperationDao operationDao;


    @Test
    void should_deposit_20() {

        //Given
        Account account = createAccount() ;

        ArgumentCaptor<Operation> operationArgumentCaptor = ArgumentCaptor.forClass(Operation.class);
        ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);

        when(accountDao.findById(anyString())).thenReturn(Optional.of(account));
        doNothing().when(accountDao).update(any(Account.class));
        doNothing().when(operationDao).save(any(Operation.class));

        //When
        Operation operation = accountService.deposit("test", BigDecimal.TEN);

        //Then

        //Account
        assertEquals(BigDecimal.valueOf(20), account.getBalance(), "should be equal");
        //Operation
        assertEquals(BigDecimal.TEN, operation.getPreviousBalance(),"");
        assertEquals(BigDecimal.valueOf(20), operation.getNewBalance(),"");
        assertEquals(BigDecimal.TEN, operation.getTransactionAmount(),"");
        assertEquals(OperationType.DEPOSIT, operation.getOperationType(), "should be equal");
        assertEquals(OperationStatus.SUCCESS, operation.getOperationStatus(), "should be equal");

        verify(operationDao).save(operationArgumentCaptor.capture());
        verify(accountDao).update(accountArgumentCaptor.capture());

    }

    @Test
    void should_withdraw_10() {

        //Given
        Account account = createAccount() ;

        ArgumentCaptor<Operation> operationArgumentCaptor = ArgumentCaptor.forClass(Operation.class);
        ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);


        when(accountDao.findById(anyString())).thenReturn(Optional.of(account));
        doNothing().when(accountDao).update(any(Account.class));
        doNothing().when(operationDao).save(any(Operation.class));

        //When
        Operation operation = accountService.withdraw("test", BigDecimal.TEN);

        //Then

        //Account
        assertEquals(BigDecimal.ZERO, account.getBalance(), "should be equal");

        //Operation
        assertEquals(BigDecimal.TEN, operation.getPreviousBalance(),"");
        assertEquals(BigDecimal.ZERO, operation.getNewBalance(),"");
        assertEquals(BigDecimal.TEN, operation.getTransactionAmount(),"");
        assertEquals(OperationType.WITHDRAW, operation.getOperationType(), "should be equal");
        assertEquals(OperationStatus.SUCCESS, operation.getOperationStatus(), "should be equal");

        verify(operationDao).save(operationArgumentCaptor.capture());
        verify(accountDao).update(accountArgumentCaptor.capture());


    }

    @Test
    void should_fail_withdraw() {

        //Given
        Account account = createAccount() ;

        when(accountDao.findById(anyString())).thenReturn(Optional.of(account));

        //When
        Operation operation = accountService.withdraw("test", BigDecimal.valueOf(300));

        //Then

        //Account
        assertEquals(BigDecimal.TEN, account.getBalance(), "should be equal");

        //Operation
        assertEquals(BigDecimal.TEN, operation.getPreviousBalance(),"");
        assertEquals(BigDecimal.TEN, operation.getNewBalance(),"");
        assertEquals(BigDecimal.valueOf(300), operation.getTransactionAmount(),"");
        assertEquals(OperationType.WITHDRAW, operation.getOperationType(), "should be equal");
        assertEquals(OperationStatus.FAIL, operation.getOperationStatus(), "should be equal");

    }

    @Test
    void should_return_10() {

        //Given
        Account account = createAccount() ;

        when(accountDao.findById(anyString())).thenReturn(Optional.of(account));

        //When
        BigDecimal balance = accountService.balance("test");

        //Then
        assertEquals(BigDecimal.TEN, balance, "should be equal");
    }

    @Test
    void should_throw_AccountNotFoundException(){
        //Given
        String message = "Account id : test is not found";
        Exception exception;
        when(accountDao.findById(anyString())).thenReturn(Optional.empty());

        //When
        exception = assertThrows(AccountNotFoundException.class, ()-> {
            accountService.withdraw("test",BigDecimal.valueOf(200));
        });

        //Then
        assertTrue(exception.getMessage().contains(message));


    }

    @Test
    void should_throw_InvalidAmountException(){
        //Given
        String message = "Amount should be greater than 0 but is [-200]";
        Exception exception;

        //When
        exception = assertThrows(InvalidAmountException.class, ()-> {
            accountService.withdraw("test",BigDecimal.valueOf(-200));
        });

        //Then
        assertTrue(exception.getMessage().contains(message));


    }

    @Test
    void should_throw_InvalidAmountException_Deposit(){
        //Given
        String message = "Amount should be greater than 0 but is [0]";
        Exception exception;

        //When
        exception = assertThrows(InvalidAmountException.class, ()-> {
            accountService.deposit("test",BigDecimal.valueOf(0));
        });

        //Then
        assertTrue(exception.getMessage().contains(message));


    }


}
