package ru.innopolis.controller.module;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.NotificationControllerImpl;
import ru.innopolis.dto.mail.EmailRequest;
import ru.innopolis.service.NotificationService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = {NotificationControllerImpl.class})
class NotificationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private NotificationService notificationService;


    @Test
    void sendNotificationControllerTest() throws Exception {
        Mockito.when(notificationService.sendNotification(Mockito.any(EmailRequest.class))).thenReturn(true);

        String adminRequestBody = """
                    {
                        "email": [
                            "ivanov@mail.ru",
                            "sidorov@mail.ru"
                        ],
                        "message": "Старт обучения по программе Java - 01.06.2025 в 12:00"
                     }
                """;

        mvc.perform(
                        post("/api/v1/notification")
                                .contentType("application/json")
                                .content(adminRequestBody)
                )
                .andExpect(status().isOk());
    }
}
