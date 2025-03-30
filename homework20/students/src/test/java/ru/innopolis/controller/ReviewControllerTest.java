package ru.innopolis.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.ReviewControllerImpl;
import ru.innopolis.dto.review.FullDataReviewResponse;
import ru.innopolis.dto.review.ReviewRequest;
import ru.innopolis.service.impl.ReviewServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ContextConfiguration(classes = {ReviewControllerImpl.class})
public class ReviewControllerTest {

    @MockitoBean
    private ReviewServiceImpl reviewService;

    @Autowired
    private MockMvc mvc;

    public ReviewControllerTest() throws ParseException {
    }


    @Test
    void createReviewControllerTest() throws Exception {
        Mockito.when(reviewService.createReview(Mockito.any(ReviewRequest.class))).thenReturn(fullDataReviewResponse);

        mvc.perform(post("/api/v1/review")
                        .contentType("application/json")
                        .content(requestReview)
                )
                .andExpect(status().isCreated());
    }


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    // create
    private final String requestReview = """
                            {
                            	"studentId": 1,
                                "coursesId": 1,
                                "text": "Положительный отзыв по курсу 'Программирование на языке JAVA'"
                            }
                        """;
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
