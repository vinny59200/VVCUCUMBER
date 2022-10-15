package com.vvauban.Cucumber3;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class StepDefinitions {

    public static final String DUMMY_LOCAL_JOKE_URL = "http://localhost:8080/vv/jokes";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WireMockServer wireMockServer;

    //To pass data between the steps
    private static final ThreadLocal<DummyContext> CTX_KEEPER = new ThreadLocal<>();

    private static DummyContext context() {
        log.error("vv dummy context null?" + (CTX_KEEPER.get() == null));
        return CTX_KEEPER.get();
    }

    @Before
    public void before() {
        CTX_KEEPER.set(new DummyContext());
        this.wireMockServer.resetAll();
    }

    @Given("^the app calls /jokes$")
    public void remoteCallOfExternalJokeApiByApp() throws Throwable {
        wireMockServer.stubFor(get(urlEqualTo("/onlinejokes")).willReturn(aResponse().withBody("""
                {
                    "id":"1",
                  	"content": "Moving to Paris would be In-Seine."
                  }
                """)));
    }

    @When("^the client call the app$")
    public void frontCallOfOurAppByClient() throws Throwable {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(DUMMY_LOCAL_JOKE_URL);
        builder.accept(MediaType.APPLICATION_JSON);
        try {
            log.error("vv in the when setting the current action");
            context().setCurrentAction(this.mockMvc.perform(builder));
        } catch (Exception e) {
            log.error("VV error in when:" + e.getMessage());
            context().setCurrentException(e);
        }
    }

    @Then("^the client receives status code of (\\d+)$")
    public void checkCallStatus(int statusCode) throws Throwable {
        log.error("vv in the then getting the current action");
        context().getCurrentAction().andExpect(status().is(statusCode));
    }

    @And("^the client receives server joke (.+)$")
    public void checkCallBody(String joke) throws Throwable {
        log.error("vv in the and getting the current action");
        context().getCurrentAction().andExpect(content().json("""
                {
                    "id":"1",
                  	"content": "Moving to Paris would be In-Seine."
                  }
                """));
    }

    @Data
    public static class DummyContext {

        private Exception currentException;
        private ResultActions currentAction;

    }
}
