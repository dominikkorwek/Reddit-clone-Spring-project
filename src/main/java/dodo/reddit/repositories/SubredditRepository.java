package dodo.reddit.repositories;

import dodo.reddit.models.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit,Long> {
    @NonNull
    Optional<Subreddit> findById(@NonNull Long id);

    @NonNull
    Optional<Subreddit> findByName(@NonNull String name);
}
