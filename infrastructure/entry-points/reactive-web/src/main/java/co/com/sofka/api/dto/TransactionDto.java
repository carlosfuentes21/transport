package co.com.sofka.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    @NotNull
    @Pattern(regexp ="^[0-9+]*$")
    @Size(min = 6, max = 8)
    private String transactionId;
    @NotNull
    private LocalDateTime timestamp;
    @NotNull
    @Pattern(regexp ="^[0-9+]*$")
    @Size(min = 6, max = 8)
    private String deviceNumber;
    @NotNull
    @Pattern(regexp ="^[0-9+]*$")
    @Size(min = 6, max = 8)
    private String userId;
    @NotNull
    @Size(min = 6, max = 40)
    private String geoPosition;
    @NotNull
    @Positive
    @Digits(integer = 10, fraction = 2)
    private Double amount;
}
