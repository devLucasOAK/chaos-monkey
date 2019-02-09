package ninja.spring.chaos.beerservice.config;

import ninja.spring.chaos.beerservice.beer.Beer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    ReactiveRedisOperations<String, Beer> redisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Beer> serializer = new Jackson2JsonRedisSerializer<>(Beer.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Beer> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, Beer> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}