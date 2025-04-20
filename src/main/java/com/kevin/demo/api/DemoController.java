package com.kevin.demo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class DemoController {
    private final static String HOST_NAME = "HOSTNAME";
    private final static String DEFAULT_NAME = "LOCAL";

    @Value("${" + HOST_NAME + ":" + DEFAULT_NAME + "}")
    private String hostname;

    @GetMapping("hello")
    public String hello() {
        return "Hello World v2.0 on %s".formatted(hostname.substring(hostname.length() - 5));
    }
}
