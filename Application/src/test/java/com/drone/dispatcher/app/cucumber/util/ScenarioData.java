package com.drone.dispatcher.app.cucumber.util;

import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ScenarioData {

    private final Map<String, Object> scenarioCache = new ConcurrentHashMap<>();

    public <T> T get(String key, Class<T> tClass) {
        Object value = scenarioCache.get(key);
        if (tClass.isInstance(value)) {
            return tClass.cast(value);
        } else {
            throw new IllegalArgumentException("Value found but not instance of passed class");
        }
    }

    public boolean contains(String key) {
        return scenarioCache.containsKey(key);
    }

    public void set(String varName, Object value) {
        scenarioCache.put(varName, value);
    }

    public void setResponseSpec(WebTestClient.ResponseSpec responseSpec) {
        set(ScenarioData.Keys.RESPONSE_SPEC, responseSpec);
    }

    public WebTestClient.ResponseSpec getResponseSpec() {
        return get(ScenarioData.Keys.RESPONSE_SPEC, WebTestClient.ResponseSpec.class);
    }

    public void clear() {
        scenarioCache.clear();
    }

    interface Keys {
        String RESPONSE_SPEC = "RESPONSE_SPEC";
    }
}
