package co.com.sofka.usecase;


import co.com.sofka.model.DailySummary;
import co.com.sofka.model.gateways.DailySummaryGateway;
import co.com.sofka.model.gateways.TransactionGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@RequiredArgsConstructor
public class DailySummaryUseCase {

    private final TransactionGateway transactionGateway;
    private final DailySummaryGateway dailySummaryGateway;

    public Mono<DailySummary> generateDailySummary() {
        LocalDate dateCurrent = LocalDate.now();
        LocalDateTime startOfDay = dateCurrent.atStartOfDay();
        LocalDateTime endOfDay = dateCurrent.atTime(LocalTime.MAX);
        return transactionGateway.sumAmountByTimestampBetween(startOfDay, endOfDay)
                .flatMap(totalAmount -> {
                    DailySummary dailySummary = DailySummary.builder()
                            .id(UUID.randomUUID().toString())
                            .date(dateCurrent)
                            .totalAmount(totalAmount)
                            .build();
                    return dailySummaryGateway.insert(dailySummary);
                });
    }

}
