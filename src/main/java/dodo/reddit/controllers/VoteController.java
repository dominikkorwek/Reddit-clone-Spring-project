package dodo.reddit.controllers;

import dodo.reddit.dto.VoteDto;
import dodo.reddit.services.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
@Slf4j
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/vote")
    public ResponseEntity<VoteDto> vote(@RequestBody VoteDto voteDto) throws Exception {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(voteService.vote(voteDto));
    }
}
