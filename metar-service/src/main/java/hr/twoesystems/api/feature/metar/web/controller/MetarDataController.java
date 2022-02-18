package hr.twoesystems.api.feature.metar.web.controller;

import hr.twoesystems.api.annotation.DefaultApiResponse;
import hr.twoesystems.api.feature.metar.web.dto.MetarDataCreateRequest;
import hr.twoesystems.api.feature.metar.web.dto.MetarDataCreateResponse;
import hr.twoesystems.api.feature.metar.web.dto.MetarDataDetailResponse;
import hr.twoesystems.api.feature.metar.web.facade.MetarDataFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/airport")
@Api(tags = "METAR", description = "METAR")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MetarDataController {

    MetarDataFacade metarDataFacade;

    @DefaultApiResponse
    @PostMapping(value = "/{iCaoCode}/METAR", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Store Metar data Api", nickname = "storeMetarData", notes = "This API is used for storing metar data")
    public ResponseEntity<Mono<MetarDataCreateResponse>> storeMetarData(@PathVariable("iCaoCode") @Valid @NotEmpty final String iCaoCode, @Valid @RequestBody @NotNull final MetarDataCreateRequest request) {
        return new ResponseEntity<>(metarDataFacade.storeMetarData(iCaoCode, request), HttpStatus.CREATED);
    }

    @DefaultApiResponse
    @GetMapping(value = "/{iCaoCode}/METAR", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retrieve metar data Api", nickname = "getMetarDataByICaoCode", notes = "This API is used for retrieving metar data with icao code")
    public ResponseEntity<Mono<MetarDataDetailResponse>> getMetarDataByICaoCode(@PathVariable("iCaoCode") @Valid @NotEmpty final String iCaoCode) {
        return new ResponseEntity<>(metarDataFacade.getMetarDataByICaoCode(iCaoCode), HttpStatus.OK);
    }
}
