package ru.innopolis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.EarthQuakeRequest;
import ru.innopolis.dto.EarthQuakeResponse;

import java.time.LocalDateTime;
import java.util.List;


@RequestMapping("/api/v1/earthquake")
public interface EarthQuakeController {

    @PostMapping
    ResponseEntity addEarthQuakes(@RequestBody EarthQuakeRequest request);

    @GetMapping("/{mag}")
    ResponseEntity<List<EarthQuakeResponse>> getEarthQuakesMag(@PathVariable Double mag);

    @GetMapping("/timebetween")
    ResponseEntity<List<EarthQuakeResponse>> getEarthQuakesTimeBetween(@RequestParam("startTime") LocalDateTime startTime, @RequestParam("endTime") LocalDateTime endTime);

}
