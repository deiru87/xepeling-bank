package com.xepelin.banking.app.domain.model;


import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class AccountCommand {

    private Long id;
    private String name;
    private BigDecimal balance;
    @Builder.Default
    private LocalDateTime eventDate = LocalDateTime.now();
    private Long version;

}
