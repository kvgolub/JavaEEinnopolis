package ru.innopolis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.dto.EarthQuakeRequest;
import ru.innopolis.dto.EarthQuakeResponse;
import ru.innopolis.entity.EarthQuakeEntity;
import ru.innopolis.repository.EarthQuakeRepository;
import ru.innopolis.service.EarthQuakeService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EarthQuakeServiceImpl implements EarthQuakeService {

    private final EarthQuakeRepository earthQuakeRepository;


    @Override
    public void addEarthQuakes(EarthQuakeRequest request) {
        List<EarthQuakeEntity> earthQuakeEntities = request.getFeatures().stream()
                .map(e -> EarthQuakeEntity.builder()
                        .title(e.getProperties().getTitle())
                        .time(LocalDateTime.ofEpochSecond(e.getProperties().getTime() / 1000, 0, ZoneOffset.of("Z")))
                        .place(e.getProperties().getPlace())
                        .mag(e.getProperties().getMag())
                        .build()
                ).toList();

        earthQuakeRepository.saveAll(earthQuakeEntities);
    }

    @Override
    public List<EarthQuakeResponse> getEarthQuakesMag(Double mag) {
        return earthQuakeRepository.findByMagAfter(mag).stream()
                .map(e -> EarthQuakeResponse.builder()
                        .id(e.getId())
                        .title(e.getTitle())
                        .time(e.getTime())
                        .place(e.getPlace())
                        .mag(e.getMag())
                        .build()
                ).toList();
    }

    @Override
    public List<EarthQuakeResponse> getEarthQuakesTimeBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return earthQuakeRepository.findByTimeBetween(startTime, endTime).stream()
                .map(e -> EarthQuakeResponse.builder()
                        .id(e.getId())
                        .title(e.getTitle())
                        .time(e.getTime())
                        .place(e.getPlace())
                        .mag(e.getMag())
                        .build()
                ).toList();
    }

}
