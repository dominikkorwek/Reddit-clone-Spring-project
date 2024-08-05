package dodo.reddit.mapper;

import dodo.reddit.dto.CommentDto;
import dodo.reddit.models.Comment;
import dodo.reddit.models.Post;
import dodo.reddit.models.User.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user", source = "user")
    Comment mapToComment(CommentDto commentDto, Post post, User user);


    @Mapping(target = "postId", source = "post.postId")
    @Mapping(target = "userName",source = "user.username")
    CommentDto mapToDto(Comment comment);
}
