package dodo.reddit.dto;

import lombok.Builder;

@Builder
public record SubredditDto(Long id, String name,
                           String description, Integer numberOfPosts) {
}
