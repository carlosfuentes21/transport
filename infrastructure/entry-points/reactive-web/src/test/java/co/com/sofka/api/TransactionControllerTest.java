package co.com.sofka.api;

import co.com.sofka.api.controller.TransactionController;
import co.com.sofka.api.dto.TransactionDto;
import co.com.sofka.api.mapper.TransactionMapper;
import co.com.sofka.usecase.TransactionUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@ContextConfiguration(classes = {TransactionController.class})
@WebFluxTest
public class TransactionControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private TransactionUseCase transactionUseCase;

    @Test
    void saveIntegrationSuccessTest() {
        TransactionDto transactionDto = TransactionDto.builder()
                .transactionId("123456")
                .deviceNumber("123456")
                .timestamp(LocalDateTime.now())
                .userId("123456")
                .geoPosition("123456, 654321")
                .amount(100.00)
                .build();

        Mockito.when(transactionUseCase.insert(Mockito.any()))
                .thenReturn(Mono.just(TransactionMapper.toDomain(transactionDto)));

        webTestClient.post()
                .uri("/api/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(transactionDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TransactionDto.class)
                .value(response -> {
                            Assertions.assertThat(response.getTransactionId()).isEqualTo("123456");
                        }
                );
    }

}
