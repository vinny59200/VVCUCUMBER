package com.vvauban.Cucumber3;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Before;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = {DummyConfig.class})
@AutoConfigureMockMvc
class Cucumber3ApplicationTests {


}
