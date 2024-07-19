package co.com.sofka.mongo.mapper;

import co.com.sofka.model.DailySummary;
import co.com.sofka.mongo.document.DailySummaryDocument;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class DailySummaryMapper {

    public static DailySummary toDomain(DailySummaryDocument dailySummaryDocument) {
        return DailySummary.builder()
                .id(dailySummaryDocument.getId())
                .date(dailySummaryDocument.getDate())
                .totalAmount(dailySummaryDocument.getTotalAmount())
                .build();
    }

    public static DailySummaryDocument toDocument(DailySummary dailySummary){
        return DailySummaryDocument.builder()
                .id(UUID.randomUUID().toString())
                .date(dailySummary.getDate())
                .totalAmount(dailySummary.getTotalAmount())
                .build();
    }
}
