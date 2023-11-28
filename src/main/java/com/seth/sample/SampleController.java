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

    LongCounter counter;

    @Autowired
    public SampleController(LongCounter longCounter) {
        this.counter = longCounter;
    }

    @GetMapping("/")
    public String helloWorld() {
        Attributes attributes = Attributes.of(AttributeKey.stringKey("Key"), "SomeWork");
        counter.add(123, attributes);
        return "Hello World";
    }
}
