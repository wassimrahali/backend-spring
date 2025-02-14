package com.backend.pfe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;




@Data
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
}

