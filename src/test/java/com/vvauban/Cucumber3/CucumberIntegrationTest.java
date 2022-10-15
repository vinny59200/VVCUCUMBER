package com.vvauban.Cucumber3;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue="com.vvauban.Cucumber3",
        features = "src/test/resources",
        plugin="pretty"
)
public class CucumberIntegrationTest {
}
