package basicSyntax.configs;



import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ExternalRateLimiterConfig {

    @Bean
    public RateLimiter apiARateLimiter() {
        return createRateLimiter("payment", 10, Duration.ofSeconds(1));
    }

    private RateLimiter createRateLimiter(
            String name,
            int limitForPeriod,
            Duration refreshPeriod) {

        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(limitForPeriod)
                .limitRefreshPeriod(refreshPeriod)
                .timeoutDuration(Duration.ZERO)
                .build();

        return RateLimiter.of(name, config);
    }
}
