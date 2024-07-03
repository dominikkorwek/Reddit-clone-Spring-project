package dodo.reddit.controllers;

import dodo.reddit.dto.SubredditDto;
import dodo.reddit.services.SubredditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@RequiredArgsConstructor
@Scope("singleton")
@Slf4j
public class SubredditController {
    private final SubredditService subredditService;

    @PostMapping("/create")
    public ResponseEntity<SubredditDto> createSubbredit(@RequestBody SubredditDto request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(subredditService.save(request));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SubredditDto>> getAllSubreddits(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getAll());
    }

}
