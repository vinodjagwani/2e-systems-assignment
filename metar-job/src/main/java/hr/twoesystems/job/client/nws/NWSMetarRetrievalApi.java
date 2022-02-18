package hr.twoesystems.job.client.nws;

import hr.twoesystems.job.client.metar.MetarServiceStorageApi;
import hr.twoesystems.job.client.metar.dto.MetarDataCreateRequest;
import hr.twoesystems.job.client.nws.config.NwsMetarPropConfig;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NWSMetarRetrievalApi {

    WebClient webClient;

    MetarServiceStorageApi metarServiceStorageApi;

    NwsMetarPropConfig nwsMetarPropConfig;

    public void metarDataRetrievalApi() {
        log.info("Start calling metar data api");
        try {
            final String url = UriComponentsBuilder.fromUriString(nwsMetarPropConfig.getUrl()).toUriString();
            log.debug("Start calling metar data api with url [{}]", url);
            getICaoCodeLinksFromPage(url).forEach(link -> {
                final String iCaoCode = link.substring(link.lastIndexOf("/") + 1, link.lastIndexOf("."));
                ofNullable(callNwsMetarDataApi(link).block())
                        .ifPresent(response -> {
                            final MetarDataCreateRequest request = new MetarDataCreateRequest();
                            request.setData(response.split("\n")[1]);
                            metarServiceStorageApi.callMetarStorageApi(iCaoCode, request).block();
                        });
            });
        } catch (final Exception ex) {
            log.error("An error occurred while calling metar data api", ex);
        }
    }

    private Mono<String> callNwsMetarDataApi(final String url) {
        return webClient.get().uri(url).retrieve().bodyToMono(new ParameterizedTypeReference<>() {
        });
    }

    private static List<String> getICaoCodeLinksFromPage(final String url) {
        final List<String> result = new LinkedList<>();
        try {
            final Parser htmlParser = new Parser(url);
            final NodeList tagNodeList = htmlParser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));
            IntStream.range(0, tagNodeList.size()).mapToObj(j -> (LinkTag) tagNodeList.elementAt(j)).map(LinkTag::getLink).filter(link -> link.endsWith(".TXT")).forEach(result::add);
            log.info("Total number of metar data links found on server during parsing [{}]", result.size());
        } catch (final ParserException e) {
            log.error("Unable to parse  [{}]", url);
        }
        return result;
    }
}
