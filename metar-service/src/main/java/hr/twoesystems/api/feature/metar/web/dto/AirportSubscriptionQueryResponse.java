package hr.twoesystems.api.feature.metar.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "AirportSubscriptionQueryResponse", description = "AirportSubscriptionQueryResponse contains response fields for querying subscription of airport")
public class AirportSubscriptionQueryResponse {

    @ApiModelProperty(position = 1, name = "iCao Code", example = "LDZA")
    String iCaoCode;
}
