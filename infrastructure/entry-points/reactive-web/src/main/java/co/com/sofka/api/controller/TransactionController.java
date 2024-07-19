package co.com.sofka.api.controller;

import co.com.sofka.api.dto.TransactionDto;
import co.com.sofka.api.mapper.TransactionMapper;
import co.com.sofka.usecase.TransactionUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionUseCase transactionUseCase;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TransactionDto> save(@Valid @RequestBody TransactionDto transactionDto) {
        return transactionUseCase.insert(TransactionMapper.toDomain(transactionDto))
                .map(TransactionMapper::toDto);
    }

}
