package com.jimicloud.dgs.dgsdemo;

import com.jimicloud.dgs.dgsdemo.repo.Review;
import com.jimicloud.dgs.dgsdemo.repo.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class ShowsDataService {

    private final SimpleShowsDataRepository<Show, String> simpleShowsDataRepository;

    public ShowsDataService(SimpleShowsDataRepository<Show, String> simpleShowsDataRepository) {
        this.simpleShowsDataRepository = simpleShowsDataRepository;
    }


    @DgsQuery
    public List<Show> fetchShows(@InputArgument String titleFilter) {
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

        if (showData.stream().anyMatch(s -> s.getTitle().equals(title))) {
            throw new IllegalArgumentException("Show with title " + title + " already exists");
        }

        Show show = Show.builder().id(UUID.randomUUID().toString()).title(title).reviews(List.of(Review.builder().starScore(starScore).build())).build();



        showData.add(show);
        return show;
    }
}
