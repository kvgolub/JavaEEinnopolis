package ru.innopolis.service.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.innopolis.client.courses.impl.CoursesClientImpl;
import ru.innopolis.dto.course.CourseResponse;
import ru.innopolis.dto.review.FullDataReviewResponse;
import ru.innopolis.dto.review.ReviewRequest;
import ru.innopolis.entity.Review;
import ru.innopolis.entity.Student;
import ru.innopolis.repository.ReviewRepository;
import ru.innopolis.repository.StudentRepository;
import ru.innopolis.service.impl.ReviewServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;


@SpringBootTest(classes = {ReviewServiceImpl.class})
public class ReviewServiceTest {

    @MockitoBean
    private ReviewRepository reviewRepository;

    @MockitoBean
    private StudentRepository studentRepository;

    @MockitoBean
    private CoursesClientImpl coursesClient;

    @Autowired
    private ReviewServiceImpl reviewService;

    public ReviewServiceTest() throws ParseException {
    }


    @Test
    void createReviewServiceTest() {
        Mockito.when(studentRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(student));
        Mockito.when(coursesClient.getOneCourse(Mockito.any(Long.class))).thenReturn(course);
        Mockito.when(reviewRepository.findReviewsByStudentIdAndCourseId(Mockito.any(Long.class), Mockito.any(Long.class))).thenReturn(review);

        Mockito.when(reviewRepository.save(Mockito.any(Review.class))).thenReturn(review);
        var response = reviewService.createReview(reviewRequest);

        Assertions.assertEquals(fullDataReviewResponse.getStudentName(), response.getStudentName());
    }


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    // create
    private final Student student = new Student(1L, "Иванов", "Иван", "Иванович", 35,"ivanov@mail.ru");
    private final CourseResponse course = new CourseResponse(1L, "Программирование на языке JAVA", sdf.parse("2024-03-01T10:00:00.000+02:00"), true);
    private final Review review = new Review(1L, 1L, 1L, "Положительный отзыв по курсу 'Программирование на языке JAVA'");

    private final ReviewRequest reviewRequest = new ReviewRequest(1L, 1L, "Положительный отзыв по курсу 'Программирование на языке JAVA'");
    private final FullDataReviewResponse fullDataReviewResponse = new FullDataReviewResponse(
            1L,
            "Иванов",
            "Иван",
            "Иванович",
            35,
            "ivanov@mail.ru",
            1L,
            "Программирование на языке JAVA",
            sdf.parse("2024-03-01T10:00:00.000+02:00"),
            true,
            "Положительный отзыв по курсу 'Программирование на языке JAVA'"
    );
}
