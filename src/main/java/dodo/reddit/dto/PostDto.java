package dodo.reddit.dto;

import lombok.Builder;

@Builder
public record PostDto(Long postId, String subredditName,String postName,
                      String url,String description, String userName,
                      Integer voteCount,Integer commentCount,String duration) {
}
