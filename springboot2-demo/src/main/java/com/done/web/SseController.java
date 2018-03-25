package com.done.web;

import com.done.model.User;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Done Lin on 2018/3/25.
 * https://segmentfault.com/a/1190000013221558
 * https://curl.haxx.se/download.html
 * https://blog.csdn.net/wulex/article/details/78065139
 */
@RestController
@RequestMapping("/sse")
public class SseController {
    @GetMapping("/randomNumbers")
    public Flux<ServerSentEvent<Integer>> randomNumbers() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                .map(data -> ServerSentEvent.<Integer>builder()
                        .event("random")
                        .id(Long.toString(data.getT1()))
                        .data(data.getT2())
                        .build());
    }

    @GetMapping(value = "/stream",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> priceStream(){
        return Flux.interval(Duration.ofMillis(1000))
                .map(l -> new User(System.currentTimeMillis()+"","done"+System.currentTimeMillis()))
                .log();
    }


    @GetMapping("/hello_world")
    public Mono<String>sayHelloWorld() throws InterruptedException {
        return Mono.just("Hello World"+System.currentTimeMillis());
    }
}