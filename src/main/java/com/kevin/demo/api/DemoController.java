package com.kevin.demo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

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
        return "Hello World v1 on %s".formatted(hostname.substring(hostname.length() - 5));
    }

    @GetMapping(value = "demo", produces = "application/vnd.kevin.random+json")
    public String random() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        String generatedString = new Random().ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return "Random string v1 = %s".formatted(generatedString);
    }

    @GetMapping(value = "demo", produces = "application/vnd.kevin.uuid+json")
    public String uuid() {
        var uuid = UUID.randomUUID();
        var buffer = ByteBuffer.allocate(16);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());
        String random = Base64.getEncoder().withoutPadding().encodeToString(buffer.array())
                .replace("/", "").replace("\\+", "-");
        return "UUID string v1 = %s".formatted(random);
    }
}
