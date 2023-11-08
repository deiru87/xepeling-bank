package com.xepelin.banking.app.infrastructure.inbound.controllers;

import com.xepelin.banking.app.application.create.TransactionCreateUseCase;
import com.xepelin.banking.app.domain.model.AccountCommand;
import com.xepelin.banking.app.domain.model.TransactionCommand;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionCreateUseCase transactionCreateUseCase;

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionCommand transactionCommand) throws Exception {
        logger.debug("Receiving request for create transaction: {}", transactionCommand);
        TransactionCommand response;
        ResponseEntity<?> responseE = this.validateTransactionTaker(transactionCommand);
        if(responseE != null){
            return responseE;
        }
        try {
            response = this.transactionCreateUseCase.createTransaction(transactionCommand);
        } catch (Exception e) {
            logger.error("Error creating transaction {}", e.getMessage());
            throw new Exception("Error creating transaction", e);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private ResponseEntity<?> validateTransactionTaker(TransactionCommand transactionCommand){
        ResponseEntity<?> responseEntity = null;
        if(transactionCommand == null || transactionCommand.getAccountId() == null ||
                transactionCommand.getAmount() == null ||
                transactionCommand.getAccountId().equals(0L)
                || transactionCommand.getAmount().compareTo(BigDecimal.ZERO) == 0){
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }


}
