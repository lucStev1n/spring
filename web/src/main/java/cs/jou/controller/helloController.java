package cs.jou.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class helloController {
    private final AmqpTemplate template;

    public helloController(AmqpTemplate template) {
        this.template = template;
    }

    @GetMapping
    public Object hello(String msg) {
        template.convertAndSend("hello", msg);
        return "hello";
    }

    @RabbitListener(queues = "hello")
    void fun(String msg) {
        log.info("receive msg {}", msg);
    }
}
