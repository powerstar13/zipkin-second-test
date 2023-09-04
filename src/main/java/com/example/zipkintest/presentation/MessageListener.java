package com.example.zipkintest.presentation;

import com.example.zipkintest.application.TestService;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Observed
@Component
class MessageListener {
    private final TestService testService;
    private final Logger log;

    public MessageListener(TestService testService) {
        this.testService = testService;
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    @RabbitListener(queues = "zipkin.user")
    void onMessage(@Header("Command") String command, UserResource user) {
        log.info("[onMessage] {}, {}", command, user);
        if (command.equals("DELETE")) {
            testService.deleteUser(user.id());
        }
    }
}
