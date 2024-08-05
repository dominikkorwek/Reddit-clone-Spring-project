package dodo.reddit.repositories;

import dodo.reddit.models.Comment;
import dodo.reddit.models.Post;
import dodo.reddit.models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByPost(Post post);

    List<Comment> findAllByUser(User user);
}
