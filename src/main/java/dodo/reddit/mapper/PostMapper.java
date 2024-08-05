package dodo.reddit.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import dodo.reddit.dto.PostDto;
import dodo.reddit.models.Post;
import dodo.reddit.models.Subreddit;
import dodo.reddit.models.User.User;
import dodo.reddit.repositories.CommentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class PostMapper {
    protected CommentRepository commentRepository;

    @Mapping(target = "postId",ignore = true)
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postDto.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target ="VoteCount",constant = "0")
    public abstract Post mapToPost(PostDto postDto, Subreddit subreddit, User user);


    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName",source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostDto mapToDto(Post post);

    protected Integer commentCount(Post post){
        return commentRepository.findAllByPost(post).size();
    }

    protected String getDuration(@NonNull Post post){
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }
}
