package hr.twoesystems.api.feature.metar.repository.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Setter
@Getter
@Table("metar")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MetarData implements Persistable<String> {

    @Id
    String metarDataId = UUID.randomUUID().toString();

    String metarData;

    @Column(value = "icao_code")
    String icaoCode;

    @Transient
    boolean isNew = true;

    @Override
    public String getId() {
        return metarDataId;
    }

    @Override
    @Transient
    public boolean isNew() {
        return isNew;
    }
}
