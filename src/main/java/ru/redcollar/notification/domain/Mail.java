package ru.redcollar.notification.domain;

import lombok.Data;

@Data
public class Mail {
    private String subject;
    private String email;
    private String text;
}
