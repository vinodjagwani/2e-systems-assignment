package hr.twoesystems.api.feature.metar.service;

import hr.twoesystems.api.feature.metar.repository.entity.MetarData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MetarDataService {

    Flux<MetarData> findAll();

    Mono<MetarData> save(final MetarData metarData);

    Mono<MetarData> findByICaoCode(final String iCaoCode);

}
