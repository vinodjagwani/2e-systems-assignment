package hr.twoesystems.job.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Getter
@Validated
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "app.metar-job")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MetarJobPropConfig {

    @NotEmpty
    String cronExp;
}
