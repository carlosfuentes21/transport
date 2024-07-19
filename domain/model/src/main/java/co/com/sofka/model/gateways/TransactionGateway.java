package co.com.sofka.model.gateways;

import co.com.sofka.model.transaction.Transaction;
import reactor.core.publisher.Mono;

public interface TransactionGateway {
    Mono<Boolean> existById(String id);
    Mono<Transaction> insert(Transaction transaction);
}
