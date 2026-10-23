package basicSyntax.configs;

import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Configuration
public class RetryConfiguration {

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig paymentConfig = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofSeconds(1))
                .retryExceptions(IOException.class)
                .build();

        RetryConfig inventoryConfig = RetryConfig.custom()
                .maxAttempts(4)
                .waitDuration(Duration.ofMillis(500))
                .retryExceptions(
                        IOException.class,
                        TimeoutException.class
                        )
                .build();

        RetryRegistry registry = RetryRegistry.ofDefaults();

        registry.retry("payment", paymentConfig);
        registry.retry("inventory", inventoryConfig);

        return registry;
    }
    /*
        maxAttempts(3): maximum 3 total attempts, including the initial call.
        waitDuration(1 second): wait one second between retry attempts.
        retryExceptions(IOException.class): retry when an IOException occurs.
     */
}
