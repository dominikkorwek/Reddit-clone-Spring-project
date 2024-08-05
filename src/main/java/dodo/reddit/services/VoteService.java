package dodo.reddit.services;

import dodo.reddit.dto.VoteDto;
import dodo.reddit.mapper.VoteMapper;
import dodo.reddit.models.Post;
import dodo.reddit.models.User.User;
import dodo.reddit.models.Vote;
import dodo.reddit.repositories.PostRepository;
import dodo.reddit.repositories.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Scope("singleton")
public class VoteService {
    private final PostRepository postRepository;
    private final VoteRepository voteRepository;
    private final AuthService authService;
    private final VoteMapper voteMapper;


    @Transactional
    public VoteDto vote(VoteDto voteDto) throws Exception {
        Post post = postRepository.findById(voteDto.postId()).orElseThrow();
        User user = authService.getCurrentUser();

        Optional<Vote> prefVoted = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,user);

        if (prefVoted.isPresent() &&
                prefVoted.get().getVoteType().equals(voteDto.voteType()))
            throw new Exception("You have already" + voteDto.voteType());

        post.setVoteCount(post.getVoteCount() + voteDto.voteType().getDirection());

        Vote vote = voteRepository.save(voteMapper.mapToVote(voteDto,post,user));
        postRepository.save(post);
        return voteMapper.mapToDto(vote);
    }
}
