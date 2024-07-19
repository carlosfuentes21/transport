package co.com.sofka.cronscheduling;

import co.com.sofka.model.DailySummary;
import co.com.sofka.usecase.DailySummaryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.scheduling.annotation.EnableScheduling;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.*;

@EnableScheduling
public class ScheduleTriggerTest {

    @Mock
    private DailySummaryUseCase dailySummaryUseCase;

    @InjectMocks
    private ScheduleTrigger scheduleTrigger;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateSummary() {
        DailySummary dailySummary = DailySummary.builder()
                .id(UUID.randomUUID().toString())
                .date(LocalDate.now())
                .totalAmount(100.0)
                .build();

        when(dailySummaryUseCase.generateDailySummary()).thenReturn(Mono.just(dailySummary));

        scheduleTrigger.generateSummary();

        verify(dailySummaryUseCase, times(1)).generateDailySummary();
    }

}
