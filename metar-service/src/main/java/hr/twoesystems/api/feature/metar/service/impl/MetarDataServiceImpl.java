package hr.twoesystems.api.feature.metar.service.impl;

import hr.twoesystems.api.feature.metar.repository.MetarDataRepository;
import hr.twoesystems.api.feature.metar.repository.entity.MetarData;
import hr.twoesystems.api.feature.metar.service.MetarDataService;
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
public class MetarDataServiceImpl implements MetarDataService {

    MetarDataRepository metarDataRepository;

    @Transactional(rollbackFor = Exception.class)
    public Mono<MetarData> save(final MetarData metarData) {
        log.debug("Start saving metar data with metarId [{}]", metarData.getMetarDataId());
        return metarDataRepository.save(metarData);
    }

    @Transactional(readOnly = true)
    public Mono<MetarData> findByICaoCode(final String iCaoCode) {
        log.debug("Start querying metar data with iCaoCode [{}]", iCaoCode);
        return metarDataRepository.findByIcaoCode(iCaoCode);
    }

    @Transactional(readOnly = true)
    public Flux<MetarData> findAll() {
        log.debug("Start querying airportSubscription");
        return metarDataRepository.findAll();
    }
}
