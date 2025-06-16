package ru.innopolis.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.innopolis.client.courses.impl.CoursesClientImpl;
import ru.innopolis.dto.course.CourseRequest;
import ru.innopolis.dto.course.CourseResponse;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@Component
public class SendCourseArchive {

    private final CoursesClientImpl coursesClient;

    public SendCourseArchive(CoursesClientImpl coursesClient) {
        this.coursesClient = coursesClient;
    }


    @Scheduled(cron = "0 * * * * *")
    public void courseResponseList() {
        List<CourseResponse> courseResponseList = coursesClient.getAllCourses();

        Date currentDatetime = Date.from(Instant.now());

        List<CourseResponse> updateCourseResponseList = new ArrayList<>();
        courseResponseList.forEach(courseResponse -> {
            if(courseResponse.getDate().before(currentDatetime)) {
                updateCourseResponseList.add(coursesClient.updateArchiveCourse(courseResponse.getId(), new CourseRequest(
                        courseResponse.getName(),
                        courseResponse.getDate(),
                        false
                )));
            }
        });

        log.info("Статус курсов обновлен!");
        updateCourseResponseList.forEach(courseResponse -> log.info("{id={}, name={}, date={}, active={}}", courseResponse.getId(), courseResponse.getName(), courseResponse.getDate(), courseResponse.getActive()));
    }
}
