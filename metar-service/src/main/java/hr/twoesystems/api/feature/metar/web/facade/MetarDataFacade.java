package hr.twoesystems.api.feature.metar.web.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.twoesystems.api.constant.MetarDataConstant;
import hr.twoesystems.api.feature.metar.repository.entity.MetarData;
import hr.twoesystems.api.feature.metar.service.MetarDataService;
import hr.twoesystems.api.feature.metar.web.dto.MetarDataCreateRequest;
import hr.twoesystems.api.feature.metar.web.dto.MetarDataCreateResponse;
import hr.twoesystems.api.feature.metar.web.dto.MetarDataDetailResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MetarDataFacade {

    ObjectMapper objectMapper;

    MetarDataService metarDataService;

    public Mono<MetarDataDetailResponse> getMetarDataByICaoCode(final String iCaoCode) {
        log.debug("Start calling querying metar data with iCaoCode [{}]", iCaoCode);
        return metarDataService.findByICaoCode(iCaoCode).map(this::buildMetarDataDetailResponse);
    }

    public Mono<MetarDataCreateResponse> storeMetarData(final String iCaoCode, final MetarDataCreateRequest request) {
        log.debug("Start calling saving metar data with request [{}]", request);
        return metarDataService.findByICaoCode(iCaoCode)
                .flatMap(result -> metarDataService.save(buildUpdateMetarData(result, request)))
                .switchIfEmpty(metarDataService.save(buildMetarData(iCaoCode, request)))
                .map(this::buildMetarDataCreateResponse);
    }

    private MetarData buildMetarData(final String iCaoCode, final MetarDataCreateRequest request) {
        final MetarData metarData = new MetarData();
        try {
            request.setData(MetarDataConstant.METAR.concat(" ").concat(request.getData()));
            metarData.setMetarData(objectMapper.writeValueAsString(request));
            metarData.setIcaoCode(iCaoCode);
        } catch (final JsonProcessingException ex) {
            log.warn("Unable to parse json due to ", ex);
        }
        return metarData;
    }

    private MetarData buildUpdateMetarData(final MetarData metarData, final MetarDataCreateRequest request) {
        try {
            request.setData(MetarDataConstant.METAR.concat(" ").concat(request.getData()));
            metarData.setMetarData(objectMapper.writeValueAsString(request));
            metarData.setNew(false);
        } catch (final JsonProcessingException ex) {
            log.warn("Unable to parse json due to ", ex);
        }
        return metarData;
    }

    private MetarDataCreateResponse buildMetarDataCreateResponse(final MetarData metarData) {
        final MetarDataCreateResponse response = new MetarDataCreateResponse();
        response.setMetarDataId(metarData.getMetarDataId());
        response.setMetarData(metarData.getMetarData());
        response.setIcaoCode(metarData.getIcaoCode());
        return response;
    }

    private MetarDataDetailResponse buildMetarDataDetailResponse(final MetarData metarData) {
        final MetarDataDetailResponse response = new MetarDataDetailResponse();
        response.setMetarPayload(metarData.getMetarData());
        return response;
    }

}
