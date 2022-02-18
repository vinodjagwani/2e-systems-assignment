package hr.twoesystems.api.feature.metar.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "MetarDataCreateRequest", description = "MetarDataCreateRequest contains request fields for creating metar data")
public class MetarDataCreateRequest {

    @NotEmpty(message = "data can't be null or empty")
    @ApiModelProperty(position = 1, name = "data", example = "{}", required = true)
    String data;
}
