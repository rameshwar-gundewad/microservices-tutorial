package com.microservices.user.services.configuration;

import com.microservices.user.services.modal.ApiResponse;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import io.github.resilience4j.common.retry.configuration.RetryConfigCustomizer;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Configuration
public class ResilienceConfig {
    @Bean
    public CircuitBreakerConfigCustomizer hotelRatingBreakerCustomizer() {
        return CircuitBreakerConfigCustomizer
                .of("hotel-rating-breaker", builder -> builder
                        .failureRateThreshold(50)
                        .waitDurationInOpenState(Duration.ofSeconds(10))
                        .slidingWindowSize(10)
                        .minimumNumberOfCalls(5)
                        .permittedNumberOfCallsInHalfOpenState(3)
                        .automaticTransitionFromOpenToHalfOpenEnabled(true)
                        .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                );
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(2)
                .waitDuration(Duration.ofMillis(1000))
                .retryOnResult(response -> {
                    if (response instanceof ApiResponse) {
                        return ((ApiResponse) response).getStatus().equals("500");
                    }
                    return false;
                })
                .retryOnException(e -> e instanceof Exception)
                .retryExceptions(IOException.class, TimeoutException.class)
                .failAfterMaxAttempts(true)
                .build();

        return RetryRegistry.of(config);
    }
}
