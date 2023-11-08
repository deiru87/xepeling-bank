package com.xepelin.banking.app.infrastructure.inbound.controllers;

import com.xepelin.banking.app.application.create.AccountCreateUseCase;
import com.xepelin.banking.app.application.find.AccountFindUseCase;
import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.model.AccountQuery;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountCreateUseCase accountCreateUseCase;
    private final AccountFindUseCase accountFindUseCase;

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountCommand accountCommand){
        logger.debug("Receiving request to create account");
        Optional<AccountQuery> accountQueryResponse = null;
        ResponseEntity<?> response = this.validateAccountTaker(accountCommand);
        if(response != null){
            return response;
        }
        try {
            accountQueryResponse = this.accountCreateUseCase.createAccount(accountCommand);
        } catch (Exception e) {
            logger.error("Error creating account: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        AccountQuery account = null;
        if (accountQueryResponse.isPresent()){
            account = accountQueryResponse.get();
        }

        return ResponseEntity.ok(account);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<?> getAccountBalance(@PathVariable Long id){
        logger.debug("Receiving request to get Balance for accountId {}", id);
        Optional<AccountQuery> accountQueryResponse = null;
        try {
            accountQueryResponse = this.accountFindUseCase.findById(id);
        } catch (Exception e) {
            logger.error("Error getting balance: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        AccountQuery accountQuery = null;
        if(accountQueryResponse.isPresent()){
            accountQuery = accountQueryResponse.get();
        }
        return ResponseEntity.ok(accountQuery);
    }


    private ResponseEntity<?> validateAccountTaker(AccountCommand accountCommand){
        ResponseEntity<?> responseEntity = null;
        if(accountCommand == null || accountCommand.getName().isEmpty() || accountCommand.getName().isBlank()){
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

}
