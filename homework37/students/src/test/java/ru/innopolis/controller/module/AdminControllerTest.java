package ru.innopolis.controller.module;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.innopolis.controller.impl.AdminControllerImpl;
import ru.innopolis.dto.mail.EmailRequest;
import ru.innopolis.kafka.KafkaProducer;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = {AdminControllerImpl.class})
public class AdminControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private KafkaProducer kafkaProducer;


    @WithMockUser(authorities="ADMIN")
    @Test
    void sendEventAdminControllerTest() throws Exception {
        Mockito.when(kafkaProducer.sendEvent(Mockito.any(EmailRequest.class))).thenReturn(true);

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
                        post("/api/v1/admin/kafka")
                                .contentType("application/json")
                                .content(adminRequestBody)
                                .with(csrf())
                )
                .andExpect(status().isOk());
    }
}