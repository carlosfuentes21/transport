package co.com.sofka.model.gateways;

import co.com.sofka.model.transaction.Transaction;
import reactor.core.publisher.Mono;

public interface RabbitGateway {
    Mono<String> sendMessage(Transaction message);
}
