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

import java.util.List;

@Service
@RequiredArgsConstructor
@Scope("singleton")
@Slf4j
@Transactional
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

    public SubredditDto get(Long id){
        return subredditMapperImpl.mapSubredditToDto(subredditRepository.findById(id).orElseThrow());
    }

}
