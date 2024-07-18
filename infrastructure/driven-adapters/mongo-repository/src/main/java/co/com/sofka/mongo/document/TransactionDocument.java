package co.com.sofka.mongo.document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Builder
@Document(collection = "transaction")
public class TransactionDocument {
    @Id
    private String transactionId;
    private LocalDateTime timestamp;
    private String deviceNumber;
    private String userId;
    private String geoPosition;
    private Double amount;
}
