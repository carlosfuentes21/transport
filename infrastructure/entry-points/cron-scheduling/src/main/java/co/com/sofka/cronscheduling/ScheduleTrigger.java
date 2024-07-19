package co.com.sofka.cronscheduling;

import co.com.sofka.usecase.DailySummaryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleTrigger {

    private final DailySummaryUseCase dailySummaryUseCase;

    @Scheduled(cron = "${app.cron.regex}")
    public void generateSummary() {
        log.info("start chron: {}", LocalDateTime.now());
        dailySummaryUseCase.generateDailySummary().subscribe();
    }

}
