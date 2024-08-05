package dodo.reddit.controllers;

import dodo.reddit.dto.CommentDto;
import dodo.reddit.services.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentsController {

    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentService.save(commentDto));
    }
    @GetMapping("/by-post/get/{id}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForPost(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.getAllCommentsForPost(id));
    }

    @GetMapping("/by-user/get/{id}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.getAllCommentsForUser(id));
    }
}
