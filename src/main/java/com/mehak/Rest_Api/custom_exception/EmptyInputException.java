package com.mehak.Rest_Api.custom_exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serial;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmptyInputException extends RuntimeException{

    @Serial
    private static final long serialVersionUID =1L;
    private String errorCode;
    private String errorMessage;
}
