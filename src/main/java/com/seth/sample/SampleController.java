package com.seth.sample;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    OpenTelemetry openTelemetry;

    @GetMapping("/")
    public String helloWorld() throws InterruptedException {
        Meter meter = openTelemetry.meterBuilder("instrumentation-library-name")
                .setInstrumentationVersion("1.0.0")
                .build();

        // Build counter e.g. LongCounter
        LongCounter counter = meter
                .counterBuilder("processed_jobs")
                .setDescription("Processed jobs")
                .setUnit("1")
                .build();

        // It is recommended that the API user keep a reference to Attributes they will record against
        Attributes attributes = Attributes.of(AttributeKey.stringKey("Key"), "SomeWork");

        // Record data
        counter.add(123, attributes);
        Thread.sleep(5000);
        return "Hello World";
    }
}
