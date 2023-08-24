package com.jimicloud.dgs.dgsdemo;

import com.jimicloud.dgs.dgsdemo.repo.Review;
import com.jimicloud.dgs.dgsdemo.repo.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.data.domain.Example;

import java.util.List;

@DgsComponent
public class ShowsDataService {

    private final ShowsDataRepository simpleShowsDataRepository;

    public ShowsDataService(ShowsDataRepository simpleShowsDataRepository) {
        this.simpleShowsDataRepository = simpleShowsDataRepository;
    }


    @DgsQuery
    public List<Show> fetchShowsWithOptionalFilter(@InputArgument String titleFilter) {
        if (titleFilter != null) {
            return simpleShowsDataRepository.findAll(Example.of(Show.builder().title(titleFilter).build()));
        }
        return simpleShowsDataRepository.findAll();
    }

    @DgsQuery
    public Show fetchShow(@InputArgument String id) {
        return simpleShowsDataRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Show with id " + id + " does not exist"));
    }

    @DgsMutation
    public Show addShow(String title, Integer starScore) {
        if (title == null || starScore == null) {
            throw new IllegalArgumentException("Both title and starScore are required");
        }

        Show show = Show.builder()
                .title(title)
                .reviews(List.of(new Review(starScore)))
                .build();

        simpleShowsDataRepository.save(show);
        return show;
    }
}
