package hr.twoesystems.job.client.metar;

import hr.twoesystems.job.client.metar.config.MetarServicePropConfig;
import hr.twoesystems.job.client.metar.dto.MetarDataCreateRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MetarServiceStorageApi {

    WebClient webClient;

    MetarServicePropConfig metarServicePropConfig;

    public Mono<ResponseEntity<Void>> callMetarStorageApi(final String iCaoCode, final MetarDataCreateRequest request) {
        log.debug("Start calling metar service storage api with iCaoCode[{}] and request [{}]", iCaoCode, request);
        try {
            final String url = UriComponentsBuilder.fromUriString(metarServicePropConfig.getUrl()).buildAndExpand(iCaoCode).toUriString();
            final Mono<ResponseEntity<Void>> response = webClient.post().uri(url)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(Mono.just(request), MetarDataCreateRequest.class)
                    .retrieve()
                    .toBodilessEntity();
            log.debug("Finish calling metar service storage api with url[{}], iCaoCode[{}] and request [{}]", url, iCaoCode, request);
            return response;
        } catch (final Exception ex) {
            log.error("An error occurred while calling metar service storage api", ex);
        }
        return Mono.just(ResponseEntity.noContent().build());
    }
}
