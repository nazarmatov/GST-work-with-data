package edu.alatoo.GST.api.exceptions;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiError {

    private int status;
    private String message;
    private Instant timestamp;
}
