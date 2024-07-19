package co.com.sofka.mongo.adapter;

import co.com.sofka.model.DailySummary;
import co.com.sofka.mongo.document.DailySummaryDocument;
import co.com.sofka.mongo.repository.DailySummaryDBRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DailySummaryAdapterTest {

    @Mock
    private DailySummaryDBRepository repository;

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private DailySummaryAdapter dailySummaryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void insertTest() {
        LocalDate localDateCurrent = LocalDate.now();
        DailySummary dailySummary = DailySummary.builder()
                .totalAmount(100.0)
                .date(localDateCurrent)
                .id("123456")
                .build();

        DailySummaryDocument dailySummaryDocument = DailySummaryDocument.builder()
                .totalAmount(100.0)
                .date(localDateCurrent)
                .id("123456")
                .build();

        when(repository.save(any(DailySummaryDocument.class))).thenReturn(Mono.just(dailySummaryDocument));

        StepVerifier.create(dailySummaryAdapter.insert(dailySummary))
                .expectSubscription()
                .expectNextMatches(response -> {
                    Assertions.assertEquals(dailySummary.getId(), response.getId());
                    return true;
                })
                .expectComplete()
                .verify();
    }

}
