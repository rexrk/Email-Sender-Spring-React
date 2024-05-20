package com.raman.emailsender.emailsenderapp.entity;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomResponse {
    private String response;
    private HttpStatus httpStatus;
    private Boolean success = false;

}
