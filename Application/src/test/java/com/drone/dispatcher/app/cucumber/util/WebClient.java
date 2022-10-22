package com.drone.dispatcher.app.cucumber.util;

import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

public class WebClient {

    private final WebTestClient webTestClient;
    private final ScenarioData scenarioData;

    public WebClient(WebTestClient webTestClient, ScenarioData scenarioData) {
        this.webTestClient = webTestClient;
        this.scenarioData = scenarioData;
    }

    public void get(String uri) {
        scenarioData.setResponseSpec(
                webTestClient
                        .get()
                        .uri(uri)
                        .exchange()
        );
    }

    public <T> void post(String uri, T body, Class<T> tClass) {
        scenarioData.setResponseSpec(
                webTestClient
                        .post()
                        .uri(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(body), tClass)
                        .exchange()
        );
    }
}
