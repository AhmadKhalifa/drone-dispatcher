package com.drone.dispatcher.app.cucumber.util;

import com.drone.dispatcher.domain.util.Clock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TestConfiguration {

    @Bean
    @Primary
    public Clock clock() {
        return Mockito.spy(new Clock());
    }
}
