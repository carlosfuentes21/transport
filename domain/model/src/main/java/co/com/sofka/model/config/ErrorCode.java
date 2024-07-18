package co.com.sofka.model.config;

import lombok.Getter;

@Getter
public enum ErrorCode {
    B409000("B409-000", 409, "Transaction already exists"),
    E500005("E500-005", 500, "Error when trying to connect to the database");

    private final String code;
    private final int httpCode;
    private final String log;

    ErrorCode(String code, int httpCode, String log) {
        this.code = code;
        this.httpCode = httpCode;
        this.log = log;
    }
}
