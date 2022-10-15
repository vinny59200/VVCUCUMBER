package com.vvauban.Cucumber3.model;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Joke {

    private String id;

    private String content;

}
