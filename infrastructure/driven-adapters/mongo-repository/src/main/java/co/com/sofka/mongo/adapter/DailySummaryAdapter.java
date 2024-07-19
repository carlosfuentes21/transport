package co.com.sofka.mongo.adapter;

import co.com.sofka.model.DailySummary;
import co.com.sofka.model.config.ErrorCode;
import co.com.sofka.model.gateways.DailySummaryGateway;
import co.com.sofka.mongo.document.DailySummaryDocument;
import co.com.sofka.mongo.helper.AdapterOperations;
import co.com.sofka.mongo.mapper.DailySummaryMapper;
import co.com.sofka.mongo.repository.DailySummaryDBRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class DailySummaryAdapter extends AdapterOperations<DailySummary, DailySummaryDocument, String, DailySummaryDBRepository> implements DailySummaryGateway {

    public DailySummaryAdapter(DailySummaryDBRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, DailySummary.class));
    }

    @Override
    public Mono<DailySummary> insert(DailySummary dailySummary) {
        return saveData(DailySummaryMapper.toDocument(dailySummary))
                .map(DailySummaryMapper::toDomain)
                .doOnError(e -> log.error(ErrorCode.E500005.getLog()));
    }

}
