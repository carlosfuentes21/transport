package co.com.sofka.mongo.document;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Builder
@Document(collection = "summary")
public class DailySummaryDocument {

    @Id
    private String id;
    private LocalDate date;
    private Double totalAmount;

}
