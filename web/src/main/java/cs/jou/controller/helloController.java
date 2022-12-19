package cs.jou.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Deque;
import java.util.LinkedList;

@Slf4j
@RestController
@RequestMapping
public class helloController {
    private final AmqpTemplate template;

    public helloController(AmqpTemplate template) {
        this.template = template;
    }

    @GetMapping("hello")
    public Object hello(String msg) {
        template.convertAndSend("hello", msg);
        return "hello";
    }

    @GetMapping
    public Object fun() {
        Deque<Object> q  = new LinkedList<>();

        System.out.println("handler");
        return "hello";
    }

    @GetMapping("a")
    public Object funa() {
        System.out.println("==> handler <==");
        return "hello a";
    }

    @GetMapping("a/{id}")
    public Object funa1(@PathVariable String id) {
        System.out.println("==> handler " + id + " <==");
        return "hello a";
    }

    @RabbitListener(queues = "hello")
    void fun(String msg) {
        log.info("receive msg {}", msg);
    }
}
