package ru.innopolis.service;

import ru.innopolis.dto.EarthQuakeRequest;
import ru.innopolis.dto.EarthQuakeResponse;

import java.time.LocalDateTime;
import java.util.List;


public interface EarthQuakeService {

    void addEarthQuakes(EarthQuakeRequest request);
    List<EarthQuakeResponse> getEarthQuakesMag(Double mag);
    List<EarthQuakeResponse> getEarthQuakesTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

}
