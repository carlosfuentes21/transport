package co.com.sofka.mongo.adapter;

import co.com.sofka.model.Transaction;
import co.com.sofka.mongo.document.TransactionDocument;
import co.com.sofka.mongo.repository.TransactionDBRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionAdapterTest {

    @Mock
    private TransactionDBRepository repository;

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private TransactionAdapter transactionAdapter;

    @BeforeEach
    void setUp() {
        repository = mock(TransactionDBRepository.class);
        mapper = mock(ObjectMapper.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void existByIdTest() {
        when(repository.existsById(anyString())).thenReturn(Mono.just(true));

        StepVerifier.create(transactionAdapter.existById("123"))
                .expectSubscription()
                .expectNextMatches(response -> {
                    Assertions.assertTrue(response);
                    return true;
                })
                .expectComplete()
                .verify();
    }

    @Test
    void insertTest() {
        Transaction transaction = Transaction.builder()
                .transactionId("123456")
                .userId("123456")
                .timestamp(LocalDateTime.now())
                .geoPosition("123456, 321456")
                .deviceNumber("123456")
                .amount(100.0)
                .build();

        TransactionDocument transactionDocument = TransactionDocument.builder()
                .transactionId("123456")
                .userId("123456")
                .timestamp(LocalDateTime.now())
                .geoPosition("123456, 321456")
                .deviceNumber("123456")
                .amount(100.0)
                .build();

        when(repository.save(any(TransactionDocument.class))).thenReturn(Mono.just(transactionDocument));

        StepVerifier.create(transactionAdapter.insert(transaction))
                .expectSubscription()
                .expectNextMatches(response -> {
                    Assertions.assertEquals(transaction.getTransactionId(), response.getTransactionId());
                    return true;
                })
                .expectComplete()
                .verify();
    }

}
