package com.seth.sample;

import io.opentelemetry.instrumentation.spring.autoconfigure.EnableOpenTelemetry;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableOpenTelemetry
public class OpenTelemetryConfig {
}
