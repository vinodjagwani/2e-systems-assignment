package hr.twoesystems.api.feature.metar.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(value = "MetarDataCreateResponse", description = "MetarDataCreateResponse contains response fields for creating metar data")
public class MetarDataCreateResponse {

    @ApiModelProperty(position = 1, name = "Metar Data Id", example = "4443242")
    String metarDataId;

    @ApiModelProperty(position = 2, name = "Metar Data", example = "{}")
    String metarData;

    @ApiModelProperty(position = 3, name = "ICao Code", example = "LDZA")
    String icaoCode;
}
