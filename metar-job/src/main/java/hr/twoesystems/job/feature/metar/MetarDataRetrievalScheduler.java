package hr.twoesystems.job.feature.metar;

import hr.twoesystems.job.client.nws.NWSMetarRetrievalApi;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MetarDataRetrievalScheduler {

    NWSMetarRetrievalApi nwsMetarRetrievalApi;

    @Scheduled(cron = "${app.metar-job.cron-exp}")
    public void performJob() {
        try {
            nwsMetarRetrievalApi.metarDataRetrievalApi();
        } catch (final Exception ex) {
            log.error("An error occurred while performing job ", ex);
        }
    }
}
