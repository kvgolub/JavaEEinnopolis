package ru.innopolis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.innopolis.client.earthquake.impl.EarthQuakeClientImpl;
import ru.innopolis.controller.impl.EarthQuakeControllerImpl;


@SpringBootApplication
public class AppEarthQuake {

    private final EarthQuakeClientImpl earthQuakeClient;
    private final EarthQuakeControllerImpl earthQuakeController;

    public AppEarthQuake(EarthQuakeClientImpl earthQuakeClient, EarthQuakeControllerImpl earthQuakeController) {
        this.earthQuakeClient = earthQuakeClient;
        this.earthQuakeController = earthQuakeController;
    }


    public static void main(String[] args) {
        SpringApplication.run(AppEarthQuake.class, args);
    }

    @Bean
    public int earthQuakeInitializationDB() {
        earthQuakeController.addEarthQuakes(earthQuakeClient.getEarthQuakes());

        return 1;
    }

}
