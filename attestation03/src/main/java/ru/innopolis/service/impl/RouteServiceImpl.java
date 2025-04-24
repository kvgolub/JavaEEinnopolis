package ru.innopolis.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.dto.route.RouteRequest;
import ru.innopolis.dto.route.RouteResponse;
import ru.innopolis.entity.Route;
import ru.innopolis.repository.RouteRepository;
import ru.innopolis.service.RouteService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    ObjectMapper objectMapper = new ObjectMapper();

    
    @Override
    public RouteResponse createRoute(RouteRequest routeRequest) {
        Route route = routeRepository.save(
                new Route(
                        null,
                        routeRequest.getNumber(),
                        routeRequest.getStartStation(),
                        routeRequest.getEndStation(),
                        routeRequest.getQuantityStation(),
                        routeRequest.getPassengerFlow()
                )
        );

        return new RouteResponse(
                route.getId(),
                route.getNumber(),
                route.getStartStation(),
                route.getEndStation(),
                route.getQuantityStation(),
                route.getPassengerFlow()
        );
    }

    @Override
    public Optional<RouteResponse> findByIdRoute(Long id) {
        try {
            Route route = routeRepository.findById(id).orElseThrow();

            return Optional.of(
                    new RouteResponse(
                            route.getId(),
                            route.getNumber(),
                            route.getStartStation(),
                            route.getEndStation(),
                            route.getQuantityStation(),
                            route.getPassengerFlow()
                    )
            );
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<RouteResponse> findAllRoutes() {
        List<Route> routeList = routeRepository.findAll();
        List<RouteResponse> routeResponseList = new ArrayList<>();
        routeList.forEach(route -> routeResponseList.add(
                new RouteResponse(
                        route.getId(),
                        route.getNumber(),
                        route.getStartStation(),
                        route.getEndStation(),
                        route.getQuantityStation(),
                        route.getPassengerFlow()
                )
        ));

        return routeResponseList;
    }

    @Override
    public Optional<RouteResponse> updateRoute(Long id, RouteRequest routeRequest) {
        try {
            Route getRoute = routeRepository.findById(id).orElseThrow();

            if (getRoute.getId() != null) {
                Route route = routeRepository.save(
                        new Route(
                                id,
                                routeRequest.getNumber(),
                                routeRequest.getStartStation(),
                                routeRequest.getEndStation(),
                                routeRequest.getQuantityStation(),
                                routeRequest.getPassengerFlow()
                        )
                );

                return Optional.of(
                        new RouteResponse(
                                route.getId(),
                                route.getNumber(),
                                route.getStartStation(),
                                route.getEndStation(),
                                route.getQuantityStation(),
                                route.getPassengerFlow()
                        )
                );
            } else {
                return Optional.empty();
            }
        } catch (EmptyResultDataAccessException | NoSuchElementException e) {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Boolean deleteByIdRoute(Long id) {
        Integer result = routeRepository.deleteByIdAsSoft(id);

        return result == 1;
    }

    @Transactional
    @Override
    public Integer deleteAllRoutes() {
        return routeRepository.deleteAllAsSoft();
    }

    @Override
    public Boolean uploadFileRoutes(MultipartFile file) throws IOException {
        try {
            File fileParse = new File("C:\\Users\\k.golub\\IdeaProjects\\JavaEEinnopolis\\attestation03\\src\\main\\resources\\json\\fileParse.json");
            file.transferTo(fileParse);

            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, RouteRequest.class);

            List<RouteRequest> routeRequestList = objectMapper.readValue(fileParse, listType);
            routeRequestList.forEach(routeRequest ->
                    routeRepository.save(
                            new Route(
                                    null,
                                    routeRequest.getNumber(),
                                    routeRequest.getStartStation(),
                                    routeRequest.getEndStation(),
                                    routeRequest.getQuantityStation(),
                                    routeRequest.getPassengerFlow()
                            )
                    )
            );

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
