package co.com.sofka.usecase;

import co.com.sofka.model.config.RedebanException;
import co.com.sofka.model.gateways.RabbitGateway;
import co.com.sofka.model.gateways.TransactionGateway;
import co.com.sofka.model.transaction.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class TransactionUseCaseTest {

    @Mock
    private TransactionGateway transactionGateway;
    @Mock
    private RabbitGateway rabbitGateway;

    @InjectMocks
    private TransactionUseCase transactionUseCase;

    Transaction transaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        transaction = Transaction.builder()
                .transactionId("123456")
                .userId("123456")
                .timestamp(LocalDateTime.now())
                .geoPosition("123456, 321456")
                .deviceNumber("123456")
                .amount(100.0)
                .build();
    }

    @Test
    void insertSuccessTest() {

        when(transactionGateway.existById(anyString())).thenReturn(Mono.just(false));
        when(transactionGateway.insert(any(Transaction.class))).thenReturn(Mono.just(transaction));

        StepVerifier.create(transactionUseCase.insert(transaction))
                .expectSubscription()
                .expectNextMatches(response -> {
                    Assertions.assertEquals(transaction.getTransactionId(), response.getTransactionId());
                    return true;
                })
                .expectComplete()
                .verify();
    }

    @Test
    void insertExceptionTest() {
        when(transactionGateway.existById(anyString())).thenReturn(Mono.just(true));

        StepVerifier.create(transactionUseCase.insert(transaction))
                .expectSubscription()
                .expectError(RedebanException.class)
                .verify();
    }

}