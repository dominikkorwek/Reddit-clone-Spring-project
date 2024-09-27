package dodo.reddit.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/spot")
@Slf4j
public class SpotController {

    public ResponseEntity<SpotDto> createSpot(@RequestBody SpotDto spotDto){

    }
}
