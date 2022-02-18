package hr.twoesystems.api.feature.metar.service.impl;

import hr.twoesystems.api.feature.metar.repository.AirportSubscriptionRepository;
import hr.twoesystems.api.feature.metar.repository.entity.AirportSubscription;
import hr.twoesystems.api.feature.metar.service.AirportSubscriptionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AirportSubscriptionServiceImpl implements AirportSubscriptionService {

    AirportSubscriptionRepository airportSubscriptionRepository;

    @Transactional(rollbackFor = Exception.class)
    public Mono<AirportSubscription> save(final AirportSubscription airportSubscription) {
        log.debug("Start saving airportSubscription with airportSubscriptionId [{}]", airportSubscription.getAirportSubscriptionId());
        return airportSubscriptionRepository.save(airportSubscription);
    }

    @Transactional(readOnly = true)
    public Mono<AirportSubscription> findByICaoCode(final String iCaoCode) {
        log.debug("Start querying airportSubscription with iCaoCode [{}]", iCaoCode);
        return airportSubscriptionRepository.findByIcaoCode(iCaoCode);
    }

    @Transactional(readOnly = true)
    public Flux<AirportSubscription> findAll() {
        log.debug("Start querying airportSubscription");
        return airportSubscriptionRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Mono<Void> delete(final AirportSubscription airportSubscription) {
        log.debug("Start deleting airportSubscription with airportSubscriptionId [{}]", airportSubscription.getAirportSubscriptionId());
        return airportSubscriptionRepository.delete(airportSubscription);
    }
}
