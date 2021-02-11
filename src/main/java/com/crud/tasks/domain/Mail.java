package com.crud.tasks.domain;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
public class Mail {

    private String mailTo;
    private String subject;
    private String message;
    private String toCc;
}
