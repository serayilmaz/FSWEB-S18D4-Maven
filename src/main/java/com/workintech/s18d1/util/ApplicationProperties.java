package com.workintech.s18d1.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

    @Value("${server.port}")
    private String serverPort;

    public String getServerPort() {
        return serverPort;
    }
}