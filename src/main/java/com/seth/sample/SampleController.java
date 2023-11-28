package com.seth.sample;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.LongCounter;
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
        Attributes attributes = Attributes
                .builder()
                .put("jobflow.id", "j-090736814F8YBXJQV5WB")
                .put("instance.id", "i-01985ec68d1740235")
                .put("service.name", "system")
                .build();
        counter.add(123, attributes);
        return "Hello World";
    }
}
