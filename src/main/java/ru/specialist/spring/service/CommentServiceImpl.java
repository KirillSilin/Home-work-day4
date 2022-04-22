package ru.specialist.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.specialist.spring.dto.CommentDto;
import ru.specialist.spring.entity.Comment;
import ru.specialist.spring.entity.Post;
import ru.specialist.spring.repository.CommentRepository;
import ru.specialist.spring.repository.PostRepository;
import ru.specialist.spring.repository.UserRepository;

import java.time.LocalDateTime;

import static ru.specialist.spring.util.SecurityUtils.getCurrentUserDetails;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void create(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setPost(parsePost(commentDto.getPostId()));
        comment.setDtCreated(LocalDateTime.now());
//        comment.setDtUpdated(LocalDateTime.now());
        UserDetails details = getCurrentUserDetails();
        comment.setUser(userRepository
                .findByUsername(details.getUsername())
                .orElseThrow());

        commentRepository.save(comment);
    }

    @Override
    public Comment findById(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        return comment;
    }

    private Post parsePost(String post) {
        return postRepository.findById(Long.parseLong(post)).orElseThrow();
    }

}
