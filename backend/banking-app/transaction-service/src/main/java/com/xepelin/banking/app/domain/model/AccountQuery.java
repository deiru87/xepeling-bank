package com.xepelin.banking.app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AccountQuery {

    private Long accountId;
    private String name;
    private BigDecimal balance;
    private LocalDateTime eventDate;
    private Long version;
}
