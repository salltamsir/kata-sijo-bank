package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.enums.OperationStatus;
import model.enums.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public  class Operation {


    private String id;
    private String accountId;
    private LocalDateTime date;
    private OperationType operationType;
    private BigDecimal previousBalance;
    private BigDecimal newBalance;
    private BigDecimal transactionAmount;
    private OperationStatus operationStatus;

    public Operation(String accountId, LocalDateTime date, OperationType operationType, BigDecimal previousBalance, BigDecimal newBalance, BigDecimal transactionAmount, boolean isSucess) {
        this.accountId = accountId;
        this.date = date;
        this.operationType = operationType;
        this.previousBalance = previousBalance;
        this.newBalance = newBalance;
        this.transactionAmount = transactionAmount;
        this.computeStatus(isSucess);
    }


    public void computeStatus(Boolean status){
        if(status){
            this.operationStatus = OperationStatus.SUCCESS;
        }
        else {
            this.operationStatus = OperationStatus.FAIL;
        }
    }

}


