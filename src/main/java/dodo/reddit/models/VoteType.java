package dodo.reddit.models;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum VoteType {
    UPVOTE(1), DOWNVOTE(-1),
    ;

    private final int direction;
}