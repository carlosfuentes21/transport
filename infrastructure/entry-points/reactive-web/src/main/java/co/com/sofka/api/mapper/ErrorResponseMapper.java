package co.com.sofka.api.mapper;

import co.com.sofka.api.dto.ResponseErrorDto;
import co.com.sofka.model.config.ErrorDictionary;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorResponseMapper {

    public static ResponseErrorDto toResponseErrorDto(ErrorDictionary errorDictionary) {
        return ResponseErrorDto.builder()
                .code(errorDictionary.getId())
                .message(errorDictionary.getMessage())
                .build();
    }

}
