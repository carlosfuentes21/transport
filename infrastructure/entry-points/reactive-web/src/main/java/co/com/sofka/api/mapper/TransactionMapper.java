package co.com.sofka.api.mapper;

import co.com.sofka.api.dto.TransactionDto;
import co.com.sofka.model.transaction.Transaction;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransactionMapper {

    public static Transaction toDomain(TransactionDto transactionDto) {
        return Transaction.builder()
                .transactionId(transactionDto.getTransactionId())
                .timestamp(transactionDto.getTimestamp())
                .deviceNumber(transactionDto.getDeviceNumber())
                .userId(transactionDto.getUserId())
                .geoPosition(transactionDto.getGeoPosition())
                .amount(transactionDto.getAmount())
                .build();
    }

    public static TransactionDto toDto(Transaction transaction) {
        return TransactionDto.builder()
                .transactionId(transaction.getTransactionId())
                .timestamp(transaction.getTimestamp())
                .deviceNumber(transaction.getDeviceNumber())
                .userId(transaction.getUserId())
                .geoPosition(transaction.getGeoPosition())
                .amount(transaction.getAmount())
                .build();
    }

}
