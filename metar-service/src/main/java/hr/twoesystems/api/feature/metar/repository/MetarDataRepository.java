package hr.twoesystems.api.feature.metar.repository;

import hr.twoesystems.api.feature.metar.repository.entity.MetarData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface MetarDataRepository extends ReactiveCrudRepository<MetarData, String> {

    Mono<MetarData> findByIcaoCode(final String icaoCode);

}
