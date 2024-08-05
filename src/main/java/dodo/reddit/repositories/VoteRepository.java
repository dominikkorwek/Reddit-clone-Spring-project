package dodo.reddit.repositories;

import dodo.reddit.models.Post;
import dodo.reddit.models.User.User;
import dodo.reddit.models.Vote;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(@NonNull Post post, User user);
}
