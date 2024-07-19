package co.com.sofka.usecase;

import co.com.sofka.model.DailySummary;
import co.com.sofka.model.gateways.DailySummaryGateway;
import co.com.sofka.model.gateways.TransactionGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DailySummaryUseCaseTest {
    @Mock
    private TransactionGateway transactionGateway;
    @Mock
    private DailySummaryGateway dailySummaryGateway;

    @InjectMocks
    private DailySummaryUseCase dailySummaryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateDailySummary() {
        DailySummary dailySummary = DailySummary.builder()
                .id(UUID.randomUUID().toString())
                .date(LocalDate.now())
                .totalAmount(100.0)
                .build();

        when(transactionGateway.sumAmountByTimestampBetween(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(Mono.just(100.0));
        when(dailySummaryGateway.insert(any(DailySummary.class))).thenReturn(Mono.just(dailySummary));

        StepVerifier.create(dailySummaryUseCase.generateDailySummary())
                .expectSubscription()
                .expectNextMatches(response -> {
                    Assertions.assertEquals(dailySummary.getId(), response.getId());
                    return true;
                })
                .expectComplete()
                .verify();
    }
}