package com.vvauban.Cucumber3.service;

import com.vvauban.Cucumber3.model.Joke;
import com.vvauban.Cucumber3.repository.JokeRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class JokeService {

    private final JokeRepository jokeRepository;

    public Joke getJoke() {
        return jokeRepository.getJoke();
    }
}
