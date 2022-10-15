package com.vvauban.Cucumber3.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vvauban.Cucumber3.model.Joke;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Repository
@Slf4j
public class JokeRepository {

    public Joke getJoke() {
        Joke fromJSON=null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet("http://localhost:8080/onlinejokes");
            HttpResponse httpResponse = httpClient.execute(request);
            fromJSON = convertResponseToString(httpResponse);
        } catch (Exception e) {

        }
        return Joke.builder().id(fromJSON.getId()).content(fromJSON.getContent()).build();

    }

    private Joke convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        ObjectMapper mapper = new ObjectMapper();
        Joke joke= mapper.readValue(responseString, Joke.class);
        return joke;
    }
}
