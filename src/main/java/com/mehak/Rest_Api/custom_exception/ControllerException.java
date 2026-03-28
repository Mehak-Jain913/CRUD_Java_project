package com.mehak.Rest_Api.custom_exception;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serial;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ControllerException extends RuntimeException{
    @Serial
    private static final long serialVersionUID =1L;
    private String errorCode;
    private String errorMessage;
}
