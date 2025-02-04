package ru.innopolis.infra;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.innopolis.service.OrderService;


@Component
public class UserMetrics {

    //рабочий вариант
    @Bean
    public MeterBinder countOrder(OrderService orderService) {
        return meterRegistry -> Gauge.builder("__countOrder", orderService::countOrder).register(meterRegistry);
    }

    @Bean
    public MeterBinder averageReceipt(OrderService orderService) {
        return meterRegistry -> Gauge.builder("__averageReceipt", orderService::averageReceipt).register(meterRegistry);
    }
}
