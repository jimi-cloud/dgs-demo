package com.jimicloud.dgs.dgsdemo;

import com.netflix.dgs.codegen.generated.types.Review;
import com.netflix.dgs.codegen.generated.types.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;
import java.util.Vector;

@DgsComponent
public class ShowsDataFetcher {

    private static final List<Show> showData = new Vector<>();

    @DgsQuery
    public List<Show> shows(@InputArgument String titleFilter) {
        if (titleFilter != null) {
            return showData.stream().filter(s -> s.getTitle().contains(titleFilter)).toList();
        }
        return showData;
    }

    @DgsMutation
    public Show addShow(String title, Integer starScore) {
        if (title == null || starScore == null) {
            throw new IllegalArgumentException("Both title and starScore are required");
        }

        Show show = Show.newBuilder()
                .title(title)
                .reviews(List.of(Review.newBuilder().starScore(starScore).build()))
                .build();

        if (showData.stream().anyMatch(s -> s.getTitle().equals(title))) {
            throw new IllegalArgumentException("Show with title " + title + " already exists");
        }

        showData.add(show);
        return show;
    }
}
