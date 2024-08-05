package dodo.reddit.controllers;

import dodo.reddit.dto.PostDto;
import dodo.reddit.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
@Slf4j
public class PostController {
    private final PostService postService;

    @PostMapping("/created")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto post){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.save(post));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.OK)
                .body(postService.getPost(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return  ResponseEntity.status(HttpStatus.OK)
                .body(postService.getAllPosts());
    }

    @GetMapping("/by-subreddit/get/{id}")
    public ResponseEntity<List<PostDto>> getPostsBySubreddit(@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.OK)
                .body(postService.getPostsBySubreddit(id));
    }

    @GetMapping("/by-user/get/{id}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.OK)
                .body(postService.getPostsByUser(id));
    }
}
