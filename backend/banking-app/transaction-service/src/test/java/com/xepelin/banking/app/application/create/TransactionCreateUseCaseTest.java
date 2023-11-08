package com.xepelin.banking.app.application.create;

import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.model.AccountQuery;
import com.xepelin.banking.app.domain.model.TransactionCommand;
import com.xepelin.banking.app.domain.model.TypeTransaction;
import com.xepelin.banking.app.domain.repository.AccountCommandRepository;
import com.xepelin.banking.app.domain.repository.AccountQueryRepository;
import com.xepelin.banking.app.domain.repository.TransactionCommandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class TransactionCreateUseCaseTest {

    @Test
    public void createTransactionWithoutErrors() throws Exception {
        TransactionCommand transactionCommand = new TransactionCommand("1", 1L, TypeTransaction.DEBIT,
                BigDecimal.valueOf(100.0), LocalDateTime.now());


        TransactionCommandRepository transactionCommandRepository = mock(TransactionCommandRepository.class);
        AccountQueryRepository accountQueryRepository = mock(AccountQueryRepository.class);
        AccountCommandRepository accountCommandRepository = mock(AccountCommandRepository.class);
        AccountQuery accountQuery = new AccountQuery(1L, "test", BigDecimal.valueOf(100.0), LocalDateTime.now(),
                0L);


        when(accountQueryRepository.findById(isA(Long.class))).thenReturn(Optional.of(accountQuery));
        doNothing().when(accountCommandRepository).updateAccount(isA(AccountCommand.class), isA(Boolean.class));
        when(transactionCommandRepository.createTransaction(isA(TransactionCommand.class))).thenReturn(transactionCommand);


        TransactionCreateUseCase transactionCreateUseCase = new TransactionCreateUseCase(transactionCommandRepository,
                accountQueryRepository, accountCommandRepository);

        TransactionCommand responseTransaction = transactionCreateUseCase.createTransaction(transactionCommand);
        Assertions.assertEquals(Long.valueOf(1), responseTransaction.getAccountId());
        Assertions.assertEquals(BigDecimal.valueOf(100.0), responseTransaction.getAmount());

    }


    @Test()
    public void createTransactionWhenNotExistAccount() throws Exception {
        TransactionCommand transactionCommand = new TransactionCommand("1", 1L, TypeTransaction.DEBIT,
                BigDecimal.valueOf(100.0), LocalDateTime.now());


        TransactionCommandRepository transactionCommandRepository = mock(TransactionCommandRepository.class);
        AccountQueryRepository accountQueryRepository = mock(AccountQueryRepository.class);
        AccountCommandRepository accountCommandRepository = mock(AccountCommandRepository.class);

        when(accountQueryRepository.findById(isA(Long.class))).thenThrow(new Exception("There is no account number:" + 1));
        TransactionCreateUseCase transactionCreateUseCase = new TransactionCreateUseCase(transactionCommandRepository,
                accountQueryRepository, accountCommandRepository);

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            transactionCreateUseCase.createTransaction(transactionCommand);
        });

        Assertions.assertEquals("There is no account number:1", thrown.getMessage());

    }

    @Test()
    public void createTransactionWhenInsufficientFunds() throws Exception {
        TransactionCommand transactionCommand = new TransactionCommand("1", 1L, TypeTransaction.DEBIT,
                BigDecimal.valueOf(100.0), LocalDateTime.now());


        TransactionCommandRepository transactionCommandRepository = mock(TransactionCommandRepository.class);
        AccountQueryRepository accountQueryRepository = mock(AccountQueryRepository.class);
        AccountCommandRepository accountCommandRepository = mock(AccountCommandRepository.class);

        AccountQuery accountQuery = new AccountQuery(1L, "test", BigDecimal.valueOf(50.0), LocalDateTime.now(),
                0L);

        when(accountQueryRepository.findById(isA(Long.class))).thenReturn(Optional.of(accountQuery));
        TransactionCreateUseCase transactionCreateUseCase = new TransactionCreateUseCase(transactionCommandRepository,
                accountQueryRepository, accountCommandRepository);

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            transactionCreateUseCase.createTransaction(transactionCommand);
        });

        Assertions.assertEquals("There is not enough money in the account", thrown.getMessage());

    }


    @Test()
    public void createTransactionWhenFailsTransaction() throws Exception {
        TransactionCommand transactionCommand = new TransactionCommand("1", 1L, TypeTransaction.DEBIT,
                BigDecimal.valueOf(100.0), LocalDateTime.now());


        TransactionCommandRepository transactionCommandRepository = mock(TransactionCommandRepository.class);
        AccountQueryRepository accountQueryRepository = mock(AccountQueryRepository.class);
        AccountCommandRepository accountCommandRepository = mock(AccountCommandRepository.class);

        AccountQuery accountQuery = new AccountQuery(1L, "test", BigDecimal.valueOf(100.0), LocalDateTime.now(),
                0L);

        when(accountQueryRepository.findById(isA(Long.class))).thenReturn(Optional.of(accountQuery));
        doNothing().when(accountCommandRepository).updateAccount(isA(AccountCommand.class), isA(Boolean.class));
        when(transactionCommandRepository.createTransaction(isA(TransactionCommand.class))).thenThrow(new Exception("Any Error"));

        TransactionCreateUseCase transactionCreateUseCase = new TransactionCreateUseCase(transactionCommandRepository,
                accountQueryRepository, accountCommandRepository);

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            transactionCreateUseCase.createTransaction(transactionCommand);
        });

        Assertions.assertEquals("Any Error", thrown.getMessage());

    }

}
