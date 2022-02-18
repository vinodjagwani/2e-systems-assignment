package hr.twoesystems.api.feature.metar.web.controller;

import hr.twoesystems.api.feature.metar.web.dto.AirportSubscriptionCreateRequest;
import hr.twoesystems.api.feature.metar.web.dto.AirportSubscriptionCreateResponse;
import hr.twoesystems.api.feature.metar.web.dto.AirportSubscriptionQueryResponse;
import hr.twoesystems.api.feature.metar.web.facade.AirportSubscriptionFacade;
import hr.twoesystems.api.utils.MockUtils;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.Optional.ofNullable;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@FieldDefaults(level = AccessLevel.PRIVATE)
@WebFluxTest(value = AirportSubscriptionController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
class AirportSubscriptionControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    AirportSubscriptionFacade airportSubscriptionFacade;

    @Test
    @SneakyThrows
    void testSubscribeAirport() {
        final String request = ofNullable(MockUtils.getResource("mock/subscribe-airport-request.json", String.class)).orElse("");
        final AirportSubscriptionCreateResponse response = ofNullable(MockUtils.getResource("mock/subscribe-airport-response.json", AirportSubscriptionCreateResponse.class)).orElse(new AirportSubscriptionCreateResponse());
        when(airportSubscriptionFacade.subscribeAirport(any(AirportSubscriptionCreateRequest.class))).thenReturn(Mono.just(response));
        webTestClient.post().uri("/v1/airport/subscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.icaoCode", is("string"));
    }

    @Test
    @SneakyThrows
    void testGetAllSubscribedAirport() {
        final AirportSubscriptionQueryResponse response = ofNullable(MockUtils.getResource("mock/subscribe-airport-response.json", AirportSubscriptionQueryResponse.class)).orElse(new AirportSubscriptionQueryResponse());
        when(airportSubscriptionFacade.getAllSubscribeAirport()).thenReturn(Flux.just(response));
        webTestClient.get().uri("/v1/airport/subscriptions")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].icaoCode", is("string"));
    }

    @Test
    @SneakyThrows
    void testDeleteSubscribeAirport() {
        when(airportSubscriptionFacade.deleteSubscribeAirport(any(String.class))).thenReturn(Mono.empty());
        webTestClient.delete().uri("/v1/airport/subscriptions/{iCaoCode}", "LDZA")
                .exchange()
                .expectStatus().isNoContent()
                .expectBody();
    }

}
