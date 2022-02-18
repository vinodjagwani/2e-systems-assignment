package hr.twoesystems.api.feature.metar.service;

import hr.twoesystems.api.feature.metar.repository.entity.AirportSubscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirportSubscriptionService {

    Flux<AirportSubscription> findAll();

    Mono<AirportSubscription> findByICaoCode(final String iCaoCode);

    Mono<Void> delete(final AirportSubscription airportSubscription);

    Mono<AirportSubscription> save(final AirportSubscription airportSubscription);
}
