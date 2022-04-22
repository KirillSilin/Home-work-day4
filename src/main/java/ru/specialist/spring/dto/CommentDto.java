package ru.specialist.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private String content;
    private String postId;
}
