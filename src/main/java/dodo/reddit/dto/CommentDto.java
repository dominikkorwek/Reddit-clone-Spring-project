package dodo.reddit.dto;

import java.time.Instant;

public record CommentDto(Long id, Long postId, Instant createdDate,
                         String text, String userName) {
}
