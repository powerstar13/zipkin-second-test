package com.example.zipkintest.presentation;

import com.example.zipkintest.application.TestService;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Observed
@RestController
public class SecondTestController {
    private final TestService testService;
    private final Logger log;

    public SecondTestController(TestService testService) {
        this.testService = testService;
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    @GetMapping("/hello")
    public String hello() {
        log.info("[hello]");
        return testService.hello();
    }

    @GetMapping("/error")
    public void error() {
        log.info("[error]");
        testService.error();
    }
}
