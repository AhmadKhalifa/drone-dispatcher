package com.drone.dispatcher.app.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/drone",
        glue = {"com.drone.dispatcher.app.cucumber.def"}
)
public class CucumberTests {

}
