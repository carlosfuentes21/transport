package co.com.sofka.mongo.repository;

import co.com.sofka.mongo.document.DailySummaryDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface DailySummaryDBRepository extends ReactiveMongoRepository<DailySummaryDocument, String>, ReactiveQueryByExampleExecutor<DailySummaryDocument> {

}
