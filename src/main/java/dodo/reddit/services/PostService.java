package dodo.reddit.services;

import dodo.reddit.dto.PostDto;
import dodo.reddit.mapper.PostMapper;
import dodo.reddit.models.Post;
import dodo.reddit.models.Subreddit;
import dodo.reddit.models.User.User;
import dodo.reddit.repositories.PostRepository;
import dodo.reddit.repositories.SubredditRepository;
import dodo.reddit.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final UserRepository userRepository;

    @Transactional
    public PostDto save(PostDto postDto) {
        Subreddit subreddit = subredditRepository.findByName(postDto.subredditName()).orElseThrow();
        User user = authService.getCurrentUser();

        Post post = postMapper.mapToPost(postDto,subreddit, user);
        postRepository.save(post);
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public PostDto getPost(Long id) {
        return postMapper.mapToDto(postRepository.findById(id).orElseThrow());
    }

    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .toList();
    }

    public List<PostDto> getPostsBySubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id).orElseThrow();
        return postRepository.findAllBySubreddit(subreddit)
                .stream()
                .map(postMapper::mapToDto)
                .toList();
    }

    public List<PostDto> getPostsByUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return postRepository.findAllByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .toList();
    }
}
