package hr.twoesystems.api.feature.metar.repository;

import hr.twoesystems.api.feature.metar.repository.entity.AirportSubscription;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AirportSubscriptionRepository extends ReactiveCrudRepository<AirportSubscription, String> {

    Mono<AirportSubscription> findByIcaoCode(final String iCaoCode);
}
