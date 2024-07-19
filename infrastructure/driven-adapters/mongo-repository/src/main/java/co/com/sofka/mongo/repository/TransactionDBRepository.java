package co.com.sofka.mongo.repository;

import co.com.sofka.mongo.document.TransactionDocument;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface TransactionDBRepository extends ReactiveMongoRepository<TransactionDocument, String>, ReactiveQueryByExampleExecutor<TransactionDocument> {
    @Aggregation(pipeline = {
            "{ '$match': { 'timestamp': { '$gte': ?0, '$lt': ?1 } } }",
            "{ '$group': { '_id': null, 'totalAmount': { '$sum': '$amount' } } }",
            "{ '$project': { 'totalAmount': 1 } }"
    })
    Mono<Double> sumAmountByTimestampBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
