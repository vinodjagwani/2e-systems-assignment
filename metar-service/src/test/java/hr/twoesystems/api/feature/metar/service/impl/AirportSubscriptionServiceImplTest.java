package hr.twoesystems.api.feature.metar.service.impl;

import hr.twoesystems.api.feature.metar.repository.AirportSubscriptionRepository;
import hr.twoesystems.api.feature.metar.repository.entity.AirportSubscription;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class AirportSubscriptionServiceImplTest {

    @Mock
    AirportSubscriptionRepository airportSubscriptionRepository;

    @InjectMocks
    AirportSubscriptionServiceImpl airportSubscriptionService;

    @Test
    void testSave() {
        when(airportSubscriptionRepository.save(any(AirportSubscription.class))).thenReturn(Mono.just(new AirportSubscription()));
        airportSubscriptionService.save(new AirportSubscription());
        verify(airportSubscriptionRepository, atLeastOnce()).save(any(AirportSubscription.class));
    }

    @Test
    void testFindByICaoCode() {
        when(airportSubscriptionRepository.findByIcaoCode(any(String.class))).thenReturn(Mono.just(new AirportSubscription()));
        airportSubscriptionService.findByICaoCode("ZDAS");
        verify(airportSubscriptionRepository, atLeastOnce()).findByIcaoCode(any(String.class));
    }

    @Test
    void testFindAll() {
        when(airportSubscriptionRepository.findAll()).thenReturn(Flux.just(new AirportSubscription()));
        airportSubscriptionService.findAll();
        verify(airportSubscriptionRepository, atLeastOnce()).findAll();
    }

    @Test
    void testDelete() {
        when(airportSubscriptionRepository.delete(any(AirportSubscription.class))).thenReturn(Mono.empty());
        airportSubscriptionService.delete(new AirportSubscription());
        verify(airportSubscriptionRepository, atLeastOnce()).delete(any(AirportSubscription.class));
    }
}
