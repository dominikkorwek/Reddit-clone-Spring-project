package dodo.reddit.mapper;

import dodo.reddit.dto.VoteDto;
import dodo.reddit.models.Post;
import dodo.reddit.models.User.User;
import dodo.reddit.models.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    @Mapping(target = "post", source = "post")
    @Mapping(target = "voteId",ignore = true)
    Vote mapToVote(VoteDto voteDto, Post post, User user);

    @Mapping(target = "postId",source = "post.postId")
    VoteDto mapToDto(Vote vote);
}
