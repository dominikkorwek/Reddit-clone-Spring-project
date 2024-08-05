package dodo.reddit.services;

import dodo.reddit.dto.CommentDto;
import dodo.reddit.mapper.CommentMapper;
import dodo.reddit.models.Comment;
import dodo.reddit.models.Post;
import dodo.reddit.models.User.User;
import dodo.reddit.repositories.CommentRepository;
import dodo.reddit.repositories.PostRepository;
import dodo.reddit.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final AuthService authService;
    private final UserRepository userRepository;

    public CommentDto save (CommentDto commentDto){
        Post post = postRepository.findById(commentDto.postId()).orElseThrow();
        User user = authService.getCurrentUser();

        Comment comment = commentMapper.mapToComment(commentDto,post,user);

        commentRepository.save(comment);

        return commentMapper.mapToDto(comment);
    }

    public List<CommentDto> getAllCommentsForPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return commentRepository.findAllByPost(post).stream()
                .map(commentMapper::mapToDto)
                .toList();
    }

    public List<CommentDto> getAllCommentsForUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return commentRepository.findAllByUser(user).stream()
                .map(commentMapper::mapToDto)
                .toList();
    }
}
