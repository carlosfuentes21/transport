package co.com.sofka.model.gateways;

import co.com.sofka.model.Transaction;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface TransactionGateway {
    Mono<Boolean> existById(String id);
    Mono<Transaction> insert(Transaction transaction);
    Mono<Double> sumAmountByTimestampBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

}
