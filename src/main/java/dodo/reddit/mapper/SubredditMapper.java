package dodo.reddit.mapper;

import dodo.reddit.dto.SubredditDto;
import dodo.reddit.models.Post;
import dodo.reddit.models.Subreddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {


    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDto mapSubredditToDto(Subreddit subreddit);

    default Integer mapPosts(List<Post> posts){
        if (posts == null)
            return 0;
        return posts.size();
    }


    @Mapping(target = "id", ignore = true)
    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user",ignore = true)
    Subreddit mapDtoToSubreddit(SubredditDto dto);
}
