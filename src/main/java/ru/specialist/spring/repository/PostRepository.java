package ru.specialist.spring.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.specialist.spring.entity.Post;
import ru.specialist.spring.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleIgnoreCase(String title);

    List<Post> findByDtCreatedBetween(LocalDateTime start, LocalDateTime end);

    List<Post> findByUser_Username(String username);

//  Написать метод для нахождеия постов, текст(content) которых включает
//  заданную подстроку без учёта регистра букв
//  "world" -> "Hello World", ...
    List<Post> findByContentContainingIgnoreCase(String substring, Sort sort);
}
