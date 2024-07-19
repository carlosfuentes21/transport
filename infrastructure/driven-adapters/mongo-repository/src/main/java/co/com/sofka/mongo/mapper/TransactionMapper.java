package co.com.sofka.mongo.mapper;

import co.com.sofka.model.Transaction;
import co.com.sofka.mongo.document.TransactionDocument;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class TransactionMapper {

    public static TransactionDocument toDocument(Transaction transaction) {
        return TransactionDocument.builder()
                .transactionId(transaction.getTransactionId())
                .timestamp(LocalDateTime.now())
                .deviceNumber(transaction.getDeviceNumber())
                .userId(transaction.getUserId())
                .geoPosition(transaction.getGeoPosition())
                .amount(transaction.getAmount())
                .build();
    }

    public static Transaction toDomain(TransactionDocument transactionDocument) {
        return Transaction.builder()
                .transactionId(transactionDocument.getTransactionId())
                .timestamp(transactionDocument.getTimestamp())
                .deviceNumber(transactionDocument.getDeviceNumber())
                .userId(transactionDocument.getUserId())
                .geoPosition(transactionDocument.getGeoPosition())
                .amount(transactionDocument.getAmount())
                .build();
    }

}
