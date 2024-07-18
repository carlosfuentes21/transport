package co.com.sofka.usecase;

import co.com.sofka.model.config.ErrorCode;
import co.com.sofka.model.config.RedebanException;
import co.com.sofka.model.gateways.RabbitGateway;
import co.com.sofka.model.gateways.TransactionGateway;
import co.com.sofka.model.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class TransactionUseCase {
    private final TransactionGateway transactionGateway;
    private final RabbitGateway rabbitGateway;

    public Mono<Transaction> insert(Transaction transaction) {
        return transactionGateway.existById(transaction.getTransactionId())
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        throw new RedebanException(ErrorCode.B409000);
                    }
                    return transactionGateway.insert(transaction)
                            .doOnNext(rabbitGateway::sendMessage);
                });
    }

}
