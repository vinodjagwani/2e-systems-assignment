package hr.twoesystems.api.feature.metar.service.impl;

import hr.twoesystems.api.feature.metar.repository.MetarDataRepository;
import hr.twoesystems.api.feature.metar.repository.entity.MetarData;
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
class MetarDataServiceImplTest {

    @Mock
    MetarDataRepository metarDataRepository;

    @InjectMocks
    MetarDataServiceImpl metarDataService;

    @Test
    void testSave() {
        when(metarDataRepository.save(any(MetarData.class))).thenReturn(Mono.just(new MetarData()));
        metarDataService.save(new MetarData());
        verify(metarDataRepository, atLeastOnce()).save(any(MetarData.class));
    }

    @Test
    void testFindByICaoCode() {
        when(metarDataRepository.findByIcaoCode(any(String.class))).thenReturn(Mono.just(new MetarData()));
        metarDataService.findByICaoCode("ZDAA");
        verify(metarDataRepository, atLeastOnce()).findByIcaoCode(any(String.class));
    }

    @Test
    void testFindAll() {
        when(metarDataRepository.findAll()).thenReturn(Flux.just(new MetarData()));
        metarDataService.findAll();
        verify(metarDataRepository, atLeastOnce()).findAll();
    }

}
