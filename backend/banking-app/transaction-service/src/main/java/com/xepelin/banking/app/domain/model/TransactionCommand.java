package com.xepelin.banking.app.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class TransactionCommand {

    private String id;
    private Long accountId;
    private TypeTransaction type;
    private BigDecimal amount;
    @Builder.Default
    private LocalDateTime eventDate = LocalDateTime.now();



}
