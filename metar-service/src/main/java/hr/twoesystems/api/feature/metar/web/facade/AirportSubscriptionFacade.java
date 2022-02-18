package hr.twoesystems.api.feature.metar.web.facade;


import hr.twoesystems.api.feature.metar.repository.entity.AirportSubscription;
import hr.twoesystems.api.feature.metar.service.AirportSubscriptionService;
import hr.twoesystems.api.feature.metar.web.dto.AirportSubscriptionCreateRequest;
import hr.twoesystems.api.feature.metar.web.dto.AirportSubscriptionCreateResponse;
import hr.twoesystems.api.feature.metar.web.dto.AirportSubscriptionQueryResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AirportSubscriptionFacade {

    AirportSubscriptionService airportSubscriptionService;

    public Mono<AirportSubscriptionCreateResponse> subscribeAirport(final AirportSubscriptionCreateRequest request) {
        log.debug("Start calling saving airportSubscription with request [{}]", request);
        return airportSubscriptionService.findByICaoCode(request.getICaoCode())
                .flatMap(result -> airportSubscriptionService.save(buildUpdateAirportSubscription(result, request)))
                .switchIfEmpty(airportSubscriptionService.save(buildAirportSubscription(request)))
                .map(this::buildAirportSubscriptionCreateResponse).log();
    }

    public Flux<AirportSubscriptionQueryResponse> getAllSubscribeAirport() {
        log.debug("Start calling querying airportSubscription");
        return airportSubscriptionService.findAll().map(this::buildAirportSubscriptionQueryResponse).log();
    }

    public Mono<Void> deleteSubscribeAirport(final String iCaoCode) {
        log.debug("Start calling delete airportSubscription with iCaoCode [{}]", iCaoCode);
        return airportSubscriptionService.findByICaoCode(iCaoCode).flatMap(airportSubscriptionService::delete).log();
    }

    private AirportSubscription buildAirportSubscription(final AirportSubscriptionCreateRequest request) {
        final AirportSubscription airportSubscription = new AirportSubscription();
        airportSubscription.setIcaoCode(request.getICaoCode());
        return airportSubscription;
    }

    private AirportSubscription buildUpdateAirportSubscription(final AirportSubscription airportSubscription, final AirportSubscriptionCreateRequest request) {
        airportSubscription.setIcaoCode(request.getICaoCode());
        airportSubscription.setNew(false);
        return airportSubscription;
    }

    private AirportSubscriptionCreateResponse buildAirportSubscriptionCreateResponse(final AirportSubscription airportSubscription) {
        final AirportSubscriptionCreateResponse response = new AirportSubscriptionCreateResponse();
        response.setICaoCode(airportSubscription.getIcaoCode());
        return response;
    }

    private AirportSubscriptionQueryResponse buildAirportSubscriptionQueryResponse(final AirportSubscription airportSubscription) {
        final AirportSubscriptionQueryResponse response = new AirportSubscriptionQueryResponse();
        response.setICaoCode(airportSubscription.getIcaoCode());
        return response;
    }
}

