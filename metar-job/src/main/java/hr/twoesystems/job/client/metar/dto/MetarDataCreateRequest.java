package hr.twoesystems.job.client.metar.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MetarDataCreateRequest {

    String data;
}
