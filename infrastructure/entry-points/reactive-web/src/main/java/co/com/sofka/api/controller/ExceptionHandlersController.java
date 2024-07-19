package co.com.sofka.api.controller;

import co.com.sofka.api.dto.ResponseErrorDto;
import co.com.sofka.api.mapper.ErrorResponseMapper;
import co.com.sofka.model.config.ErrorCode;
import co.com.sofka.model.config.ErrorDictionary;
import co.com.sofka.model.config.RedebanException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@ControllerAdvice
public class ExceptionHandlersController {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ResponseErrorDto>> handleWebExchangeBindException(WebExchangeBindException e) {
        return genericHandleException(ErrorCode.B400002, e);
    }

    @ExceptionHandler(RedebanException.class)
    public Mono<ResponseEntity<ResponseErrorDto>> handleRedebanException(RedebanException e) {
        return genericHandleException(e.getError(), e);
    }

    private Mono<ResponseEntity<ResponseErrorDto>> genericHandleException(ErrorCode error, Exception e) {
        ErrorDictionary errorDictionary = ErrorDictionary.builder()
                .httpStatus(error.getHttpCode())
                .message(error.getLog())
                .id(error.getCode())
                .build();

        return Mono.just(getResponseEntity(errorDictionary, e));
    }

    public ResponseEntity<ResponseErrorDto> getResponseEntity(ErrorDictionary error, Exception e) {
        return ResponseEntity
                .status(Objects.requireNonNull(HttpStatus.resolve(error.getHttpStatus())))
                .headers(getHeaders(e.getMessage()))
                .body(ErrorResponseMapper.toResponseErrorDto(error));
    }

    private HttpHeaders getHeaders(String message) {
        log.error("Error message : {}", message);
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", message);
        return headers;
    }

}
