package ru.innopolis.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.controller.EarthQuakeController;
import ru.innopolis.dto.EarthQuakeRequest;
import ru.innopolis.dto.EarthQuakeResponse;
import ru.innopolis.service.impl.EarthQuakeServiceImpl;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
public class EarthQuakeControllerImpl implements EarthQuakeController {

    private final EarthQuakeServiceImpl earthQuakeServiceImpl;


    // https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.geojson
    @Override
    public ResponseEntity addEarthQuakes(EarthQuakeRequest request) {
        log.info("Старт добавления землетрясений");
        earthQuakeServiceImpl.addEarthQuakes(request);
        log.info("{} землетрясений успешно добавлено", request.getFeatures().size());

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<EarthQuakeResponse>> getEarthQuakesMag(Double mag) {
        log.info("Запрос на получение землетрясений с магнитудой выше чем: {}", mag);
        var response = earthQuakeServiceImpl.getEarthQuakesMag(mag);
        responseLog(response.size());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<EarthQuakeResponse>> getEarthQuakesTimeBetween(LocalDateTime startTime, LocalDateTime endTime) {
        log.info("Запрос на получение землетрясений в период времени от {} до {}", startTime, endTime);
        var response = earthQuakeServiceImpl.getEarthQuakesTimeBetween(startTime, endTime);
        responseLog(response.size());

        return ResponseEntity.ok(response);
    }


    private void responseLog(int earthQuakeSize) {
        log.info("Найдено {} землетрясений", earthQuakeSize);
    }

}
