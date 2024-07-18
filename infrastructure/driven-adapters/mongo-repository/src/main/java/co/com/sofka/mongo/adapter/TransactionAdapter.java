package co.com.sofka.mongo.adapter;

import co.com.sofka.model.config.ErrorCode;
import co.com.sofka.model.config.RedebanException;
import co.com.sofka.model.gateways.TransactionGateway;
import co.com.sofka.model.transaction.Transaction;
import co.com.sofka.mongo.document.TransactionDocument;
import co.com.sofka.mongo.helper.AdapterOperations;
import co.com.sofka.mongo.mapper.TransactionMapper;
import co.com.sofka.mongo.repository.TransactionDBRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class TransactionAdapter extends AdapterOperations<Transaction, TransactionDocument, String, TransactionDBRepository> implements TransactionGateway {

    public TransactionAdapter(TransactionDBRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Transaction.class));
    }

    @Override
    public Mono<Boolean> existById(String id) {
        return repository.existsById(id)
                .onErrorResume(e -> Mono.error(new RedebanException(ErrorCode.E500005)));
    }

    @Override
    public Mono<Transaction> insert(Transaction transaction) {
        return saveData(TransactionMapper.toDocument(transaction))
                .map(TransactionMapper::toDomain)
                .onErrorResume(e -> Mono.error(new RedebanException(ErrorCode.E500005)));
    }

}
