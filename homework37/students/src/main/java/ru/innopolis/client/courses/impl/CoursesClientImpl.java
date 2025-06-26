package ru.innopolis.client.courses.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.innopolis.client.courses.CoursesClient;
import ru.innopolis.dto.course.CourseResponse;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


@Component
//@Profile("restClient")
public class CoursesClientImpl implements CoursesClient {

    private RestClient restClient;
    private static final String URL = "http://localhost:8082/api/v1/course";

    @PostConstruct
    private void init() {
        restClient = RestClient.builder()
                .baseUrl(URL)
                .build();
    }

    @Override
    public CourseResponse getOneCourse(Long id) {
        return restClient.get()
                .uri("/" + id)
                .retrieve()
                .body(CourseResponse.class);
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        ParameterizedTypeReference<List<CourseResponse>> type = new ParameterizedTypeReference<>() {
        };

        return restClient.get()
                .exchange((clientRequest, clientResponse) -> {
                    if (clientResponse.getStatusCode().is2xxSuccessful()) {
                        return clientResponse.bodyTo(type);
                    }
                    if (clientResponse.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
                        throw new NoSuchElementException("Данные не найдены");
                    }
                    return List.of();
                });
    }


    @Override
    public List<CourseResponse> getListCoursesByIds(Set<Long> ids) {
        ParameterizedTypeReference<List<CourseResponse>> type = new ParameterizedTypeReference<>() {
        };

        return restClient.post()
                .uri("/definite_courses")
                .body(ids)
                .retrieve()
                .body(type);
    }
}
