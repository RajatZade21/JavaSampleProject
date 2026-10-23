package basicSyntax.configs;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfiguration {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {

        CircuitBreakerConfig paymentConfig =
                CircuitBreakerConfig.custom()
                        .slidingWindowType(
                                CircuitBreakerConfig.SlidingWindowType.COUNT_BASED
                        )
                        .failureRateThreshold(50)
                        .slidingWindowSize(10)
                        .minimumNumberOfCalls(5)
                        .waitDurationInOpenState(
                                Duration.ofSeconds(10))
                        .permittedNumberOfCallsInHalfOpenState(3)
                        .build();

        CircuitBreakerConfig inventoryConfig =
                CircuitBreakerConfig.custom()
                        .slidingWindowType(
                                CircuitBreakerConfig.SlidingWindowType.TIME_BASED
                        )
                        .failureRateThreshold(40)
                        .slidingWindowSize(20)
                        .minimumNumberOfCalls(10)
                        .waitDurationInOpenState(
                                Duration.ofSeconds(15))
                        .permittedNumberOfCallsInHalfOpenState(5)
                        .build();

        CircuitBreakerRegistry registry =
                CircuitBreakerRegistry.ofDefaults();

        registry.circuitBreaker("payment", paymentConfig);
        registry.circuitBreaker("inventory", inventoryConfig);

        return registry;
    }
}
