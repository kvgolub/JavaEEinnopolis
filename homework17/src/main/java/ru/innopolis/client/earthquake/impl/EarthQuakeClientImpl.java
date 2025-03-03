package ru.innopolis.client.earthquake.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.innopolis.client.earthquake.EarthQuakeClient;
import ru.innopolis.dto.EarthQuakeRequest;


@Component
public class EarthQuakeClientImpl implements EarthQuakeClient {

    private RestClient restClient;
    private static final String URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson";

    @PostConstruct
    private void init() {
        restClient = RestClient.builder()
                .baseUrl(URL)
                .build();
    }


    @Override
    public EarthQuakeRequest getEarthQuakes() {
        return restClient.get()
                .retrieve()
                .body(EarthQuakeRequest.class);
    }

}
