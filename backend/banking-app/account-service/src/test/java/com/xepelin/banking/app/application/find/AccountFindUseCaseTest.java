package com.xepelin.banking.app.application.find;

import com.xepelin.banking.app.application.create.AccountCreateUseCase;
import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.model.AccountQuery;
import com.xepelin.banking.app.domain.repository.AccountCommandRepository;
import com.xepelin.banking.app.domain.repository.AccountQueryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountFindUseCaseTest {

    @Test
    public void findAccountTest() throws Exception {
        AccountQueryRepository accountQueryRepository = mock(AccountQueryRepository.class);
        AccountQuery accountQuery = new AccountQuery(1L, "test", BigDecimal.TEN, LocalDateTime.now(), 0L);
        when(accountQueryRepository.findById(isA(Long.class))).thenReturn(Optional.of(accountQuery));
        AccountFindUseCase accountFindUseCase = new AccountFindUseCase(accountQueryRepository);
        AccountQuery response = accountFindUseCase.findById(1L).get();
        Assertions.assertEquals("test", response.getName());
        Assertions.assertEquals(BigDecimal.TEN, response.getBalance());

    }

    @Test
    public void findAccountWhenErrorTest() throws Exception {
        AccountQueryRepository accountQueryRepository = mock(AccountQueryRepository.class);
        AccountQuery accountQuery = new AccountQuery(1L, "test", BigDecimal.TEN, LocalDateTime.now(), 0L);
        when(accountQueryRepository.findById(isA(Long.class))).thenThrow(new Exception("Any Error"));
        AccountFindUseCase accountFindUseCase = new AccountFindUseCase(accountQueryRepository);

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            AccountQuery response = accountFindUseCase.findById(1L).get();
        });

        Assertions.assertEquals("Any Error", thrown.getMessage());

    }
}
