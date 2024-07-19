package co.com.sofka.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
public class ResponseErrorDto {
    private String code;
    private String message;
}
