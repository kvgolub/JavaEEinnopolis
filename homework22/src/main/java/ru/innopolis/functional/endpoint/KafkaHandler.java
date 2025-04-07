package ru.innopolis.functional.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.innopolis.kafka.KafkaProducer;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaHandler {

    private final KafkaProducer kafkaProducer;

    public Mono<ServerResponse> sendMessageToKafka(ServerRequest serverRequest) {

        var count = Integer.valueOf(serverRequest
                .queryParam("count")
                .orElse("10"));

        return kafkaProducer.sendMessage(count)
                .then()
                .flatMap(e -> ServerResponse.ok()
                        .body(Mono.just("Отправлено сообщение"), String.class));
    }

}
