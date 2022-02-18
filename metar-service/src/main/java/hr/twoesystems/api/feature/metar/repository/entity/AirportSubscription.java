package hr.twoesystems.api.feature.metar.repository.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Setter
@Getter
@Table("airport_subscription")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirportSubscription implements Persistable<String> {

    @Id
    String airportSubscriptionId = UUID.randomUUID().toString();

    String icaoCode;

    @Transient
    boolean isNew = true;

    @Override
    public String getId() {
        return airportSubscriptionId;
    }

    @Override
    @Transient
    public boolean isNew() {
        return isNew;
    }
}
