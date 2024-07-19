package co.com.sofka.api.mapper;

import co.com.sofka.api.dto.TransactionDto;
import co.com.sofka.model.Transaction;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class TransactionMapper {

    public static Transaction toDomain(TransactionDto transactionDto) {
        return Transaction.builder()
                .transactionId(transactionDto.getTransactionId())
                .timestamp(convertStringToLocalDateTime(transactionDto.getTimestamp()))
                .deviceNumber(transactionDto.getDeviceNumber())
                .userId(transactionDto.getUserId())
                .geoPosition(transactionDto.getGeoPosition())
                .amount(transactionDto.getAmount())
                .build();
    }

    public static TransactionDto toDto(Transaction transaction) {
        return TransactionDto.builder()
                .transactionId(transaction.getTransactionId())
                .timestamp(convertLocalDateTimeToString(transaction.getTimestamp()))
                .deviceNumber(transaction.getDeviceNumber())
                .userId(transaction.getUserId())
                .geoPosition(transaction.getGeoPosition())
                .amount(transaction.getAmount())
                .build();
    }

    public static LocalDateTime convertStringToLocalDateTime(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(timestamp, formatter);
    }

    public static String convertLocalDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return localDateTime.format(formatter);
    }

}
