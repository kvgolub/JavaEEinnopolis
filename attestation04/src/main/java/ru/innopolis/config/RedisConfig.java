package ru.innopolis.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.time.Duration;


@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        // Запуск локально через IDE
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 9002);

        // Запуск в контейнере через docker-compose
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("bus_station_redis", 6379);

        return new JedisConnectionFactory(config);
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(300));

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(cacheConfiguration)
                .build();
    }
}
