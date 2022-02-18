package hr.twoesystems.api.feature.metar.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "MetarDataDetailResponse", description = "MetarDataDetailResponse contains response fields for retrieving metar data")
public class MetarDataDetailResponse {

    @ApiModelProperty(position = 1, name = "Metar Payload", example = "{}", required = true)
    String metarPayload;
}
