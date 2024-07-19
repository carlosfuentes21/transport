package co.com.sofka.model.gateways;

import co.com.sofka.model.DailySummary;
import reactor.core.publisher.Mono;

public interface DailySummaryGateway {
    Mono<DailySummary> insert(DailySummary dailySummary);
}
