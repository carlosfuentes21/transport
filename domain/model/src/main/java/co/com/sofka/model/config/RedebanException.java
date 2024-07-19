package co.com.sofka.model.config;

import lombok.Getter;

@Getter
public class RedebanException extends RuntimeException {
    private final ErrorCode error;

    public RedebanException(ErrorCode errorCode) {
        super(errorCode.getLog());
        this.error = errorCode;
    }

}
