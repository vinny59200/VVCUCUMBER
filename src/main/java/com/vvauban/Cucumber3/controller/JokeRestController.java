package com.vvauban.Cucumber3.controller;

import com.vvauban.Cucumber3.model.Joke;
import com.vvauban.Cucumber3.service.JokeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/vv", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class JokeRestController {

    private final JokeService jokeService;

    @RequestMapping("/jokes")
    public Joke getJokes() {
        return jokeService.getJoke();
    }
}
