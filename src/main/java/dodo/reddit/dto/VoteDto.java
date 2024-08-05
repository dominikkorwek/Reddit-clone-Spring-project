package dodo.reddit.dto;

import dodo.reddit.models.VoteType;

public record VoteDto(VoteType voteType,Long postId) {
}
