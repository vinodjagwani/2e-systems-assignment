package hr.twoesystems.api.feature.metar.web.controller;

import hr.twoesystems.api.annotation.DefaultApiResponse;
import hr.twoesystems.api.feature.metar.web.dto.AirportSubscriptionCreateRequest;
import hr.twoesystems.api.feature.metar.web.dto.AirportSubscriptionCreateResponse;
import hr.twoesystems.api.feature.metar.web.dto.AirportSubscriptionQueryResponse;
import hr.twoesystems.api.feature.metar.web.facade.AirportSubscriptionFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/airport/subscriptions")
@Api(tags = "Airport Subscription", description = "Airport Subscription")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AirportSubscriptionController {

    AirportSubscriptionFacade airportSubscriptionFacade;

    @DefaultApiResponse
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Subscribe Airport Api", nickname = "subscribeAirport", notes = "This API is used for subscription of airport")
    public ResponseEntity<Mono<AirportSubscriptionCreateResponse>> subscribeAirport(@Valid @RequestBody @NotNull final AirportSubscriptionCreateRequest request) {
        return new ResponseEntity<>(airportSubscriptionFacade.subscribeAirport(request), HttpStatus.CREATED);
    }

    @DefaultApiResponse
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Query all subscribed Airports Api", nickname = "getAllSubscribedAirport", notes = "This API is used for get all subscribed airports")
    public ResponseEntity<Flux<AirportSubscriptionQueryResponse>> getAllSubscribedAirport() {
        return new ResponseEntity<>(airportSubscriptionFacade.getAllSubscribeAirport(), HttpStatus.OK);
    }

    @DefaultApiResponse
    @DeleteMapping(value = "/{iCaoCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete subscribed Airports Api", nickname = "deleteSubscribedAirport", notes = "This API is used for delete subscribed airports")
    public ResponseEntity<Mono<Void>> deleteSubscribedAirport(@PathVariable("iCaoCode") final String iCaoCode) {
        return new ResponseEntity<>(airportSubscriptionFacade.deleteSubscribeAirport(iCaoCode), HttpStatus.NO_CONTENT);
    }
}
