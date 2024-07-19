package co.com.sofka.model.config;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDictionary {
    private String id;
    private Integer httpStatus;
    private String message;
}
