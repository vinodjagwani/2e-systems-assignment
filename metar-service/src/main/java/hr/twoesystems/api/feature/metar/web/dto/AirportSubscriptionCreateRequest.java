package hr.twoesystems.api.feature.metar.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "AirportSubscriptionCreateRequest", description = "AirportSubscriptionCreateRequest contains request fields for creating subscription of airport")
public class AirportSubscriptionCreateRequest {

    @NotEmpty(message = "iCaoCode can't be null or empty")
    @ApiModelProperty(position = 1, name = "iCao Code", example = "LDZA", required = true)
    String iCaoCode;
}
