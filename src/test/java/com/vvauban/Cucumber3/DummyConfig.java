package com.vvauban.Cucumber3;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;


@TestConfiguration
@ActiveProfiles("test")
@Slf4j
public class DummyConfig {

    @Bean(destroyMethod = "shutdown")
    public WireMockServer wireMockServer() {
        WireMockServer server = new WireMockServer();
        server.start();
        return server;
    }
}
