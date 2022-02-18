package hr.twoesystems.api.feature.metar.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "AirportSubscriptionCreateResponse", description = "AirportSubscriptionCreateResponse contains response fields for creating subscription of airport")
public class AirportSubscriptionCreateResponse {

    @ApiModelProperty(position = 1, name = "iCao Code", example = "LDZA")
    String iCaoCode;
}
