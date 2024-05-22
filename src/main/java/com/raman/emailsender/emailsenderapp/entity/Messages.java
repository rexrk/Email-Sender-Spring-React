package com.raman.emailsender.emailsenderapp.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Messages {
    private String from;
    private String subject;
    private String content;
    private List<String> files;
}
