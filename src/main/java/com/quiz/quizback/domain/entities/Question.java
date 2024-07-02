package com.quiz.quizback.domain.entities;

import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    String id;
    String description;
    byte score;
    List<Option> options;

}
