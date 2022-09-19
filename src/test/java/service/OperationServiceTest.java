package service;

import dao.IOperationDao;
import model.Operation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.TestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationServiceTest {

    @InjectMocks
    private OperationService operationService;

    @Mock
    IOperationDao operationDao;

    @Test
    void should_return_operationList() {
        Operation operation = TestUtils.createDepositOperation();

        when(operationDao.findAll(anyString())).thenReturn(Arrays.asList(operation));

        List<Operation> operations = operationService.findByAccountId("test");

        assertEquals(1, operations.size());
        assertEquals(operation, operations.get(0));


    }
}
