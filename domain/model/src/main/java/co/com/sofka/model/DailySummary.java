package co.com.sofka.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class DailySummary {
    private String id;
    private LocalDate date;
    private Double totalAmount;
}
