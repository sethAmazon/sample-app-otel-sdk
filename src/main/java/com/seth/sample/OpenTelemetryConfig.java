package com.seth.sample;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.instrumentation.spring.autoconfigure.EnableOpenTelemetry;
import io.opentelemetry.instrumentation.spring.autoconfigure.resources.OtelResourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableOpenTelemetry
public class OpenTelemetryConfig {
    @Bean
    public Meter meter(OpenTelemetry openTelemetry) {
        return openTelemetry.meterBuilder("instrumentation-library-name")
                .setInstrumentationVersion("1.0.0")
                .build();
    }

    @Bean
    public LongCounter LongCounter(Meter meter, OtelResourceProperties otelResourceProperties) {
        LongCounter longCounter = meter
                .counterBuilder("processed_jobs")
                .setDescription("Processed jobs")
                .setUnit("1")
                .build();
        Map<String, String> map = new HashMap<>();
        map.put("service.name", "seth-service");
        otelResourceProperties.setAttributes(map);
        return longCounter;
    }
}
