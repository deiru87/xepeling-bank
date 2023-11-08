package com.xepelin.banking.app.infrastructure.share;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TransactionEvent {

    private String transactionId;
    private Long accountId;
    private BigDecimal amount;

}
