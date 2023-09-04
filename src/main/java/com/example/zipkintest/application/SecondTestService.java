package com.example.zipkintest.application;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Observed
@Service
public class SecondTestService implements TestService {
    private final RestTemplate restTemplate;
    private final Logger log;

    public SecondTestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public void deleteUser(String id) {
        log.info("[deleteUser] id={}", id);
        restTemplate.delete("http://localhost:8080/user/{id}", id);
    }

    @Override
    public String hello() {
        log.info("[hello]");
        return "world";
    }

    @Override
    public void error() {
        log.error("[error]");
        throw new RuntimeException("error test");
    }
}
