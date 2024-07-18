package co.com.sofka.mongo.repository;

import co.com.sofka.mongo.document.TransactionDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface TransactionDBRepository extends ReactiveMongoRepository<TransactionDocument, String>, ReactiveQueryByExampleExecutor<TransactionDocument> {

}
