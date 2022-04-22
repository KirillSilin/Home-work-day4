package ru.specialist.spring.service;

import ru.specialist.spring.dto.CommentDto;
import ru.specialist.spring.entity.Comment;

public interface CommentService {
    void create(CommentDto commentDto);

    Comment findById(Long commentId);

}
