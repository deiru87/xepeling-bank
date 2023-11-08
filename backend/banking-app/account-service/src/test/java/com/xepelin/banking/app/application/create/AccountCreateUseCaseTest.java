package com.xepelin.banking.app.application.create;

import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.model.AccountQuery;
import com.xepelin.banking.app.domain.repository.AccountCommandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountCreateUseCaseTest {

    @Test
    public void createAccountTest() throws Exception {
        AccountCommandRepository accountCommandRepository = mock(AccountCommandRepository.class);
        AccountQuery accountQuery = new AccountQuery(1L, "test", BigDecimal.TEN, LocalDateTime.now(), 0L);
        when(accountCommandRepository.createAccount(isA(AccountCommand.class))).thenReturn(Optional.of(accountQuery));
        AccountCreateUseCase accountCreateUseCase = new AccountCreateUseCase(accountCommandRepository);
        AccountQuery response = accountCreateUseCase.createAccount(AccountCommand.builder().build()).get();
        Assertions.assertEquals("test", response.getName());
        Assertions.assertEquals(BigDecimal.TEN, response.getBalance());

    }

    @Test
    public void createAccountWhenErrorTest() throws Exception {
        AccountCommandRepository accountCommandRepository = mock(AccountCommandRepository.class);
        AccountQuery accountQuery = new AccountQuery(1L, "test", BigDecimal.TEN, LocalDateTime.now(), 0L);
        when(accountCommandRepository.createAccount(isA(AccountCommand.class))).thenThrow(new Exception("Any Error"));
        AccountCreateUseCase accountCreateUseCase = new AccountCreateUseCase(accountCommandRepository);

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            AccountQuery response = accountCreateUseCase.createAccount(AccountCommand.builder().build()).get();
        });

        Assertions.assertEquals("Any Error", thrown.getMessage());

    }

}
