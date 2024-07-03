package dodo.reddit.services;

import dodo.reddit.dto.SubredditDto;
import dodo.reddit.mapper.SubredditMapper;
import dodo.reddit.models.Subreddit;
import dodo.reddit.repositories.SubredditRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Scope("singleton")
@Slf4j

public class SubredditService {
    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapperImpl;

    @Transactional
    public SubredditDto save(SubredditDto request){

         Subreddit subreddit = subredditRepository
                .save(subredditMapperImpl
                        .mapDtoToSubreddit(request));


        return subredditMapperImpl.mapSubredditToDto(subreddit);
    }

    @Transactional
    public List<SubredditDto> getAll() {
         return subredditRepository.findAll()
                .stream()
                .map(subredditMapperImpl::mapSubredditToDto)
                .toList();
    }

    public Optional<SubredditDto> get(Long id){
        return subredditRepository.findOne()
    }

}
