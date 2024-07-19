package co.com.sofka.model.gateways;

import co.com.sofka.model.Transaction;
import reactor.core.publisher.Mono;

public interface RabbitGateway {
    Mono<String> sendMessage(Transaction message);
}
