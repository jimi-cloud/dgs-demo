package com.jimicloud.dgs.dgsdemo;

import com.netflix.dgs.codegen.generated.types.Show;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ShowsDataService.class})
class ShowsDataTest {

    @Test
    void testNullTitle_WithException() {
        SimpleShowsDataRepository<Show, String>  simpleShowsDataRepository = new SimpleShowsDataRepository<Show, String> (null, null));
        ShowsDataService showsDataService = new ShowsDataService(simpleShowsDataRepository);
        assertThrows(IllegalArgumentException.class,
                () -> showsDataService.addShow(null, 10),
                "Should throw 'IllegalArgumentException'"
        );
    }

    @Test
    void testNullStarScore_WithException() {
        ShowsDataService showsDataService = new ShowsDataService(showsDataRepository);
        assertThrows(IllegalArgumentException.class,
                () -> showsDataService.addShow("Gamer", null),
                "Should throw 'IllegalArgumentException'"
        );
    }

    @Test
    void testShowsDataFetcher_ThreeShowsListed() {
        ShowsDataService showsDataService = new ShowsDataService(showsDataRepository);
        List<Show> x = showsDataService.fetchShows(null);
        assertNotNull(x, "Should not be null");
        assertEquals(x.size(), 0, "Should be '0' shows listed");
    }

    @Test
    void testAddNewShow_AddedAndQueried() {
        ShowsDataService showsDataService = new ShowsDataService(showsDataRepository);
        Show x = showsDataService.addShow("The Witcher 4", 5);
        List<Show> shows = showsDataService.fetchShows("The Witcher 4");
        assertNotNull(x, "Should 'not be null'");
        assertNotNull(shows.get(0), "Should 'not be null'");
        assertEquals(shows.get(0).getTitle(), "The Witcher 4", "Should be 'The Witcher'");
        assertEquals(shows.get(0).getReviews().get(0).getStarScore(), 5, "Should be '5' stars");
    }

    @Test
    void testAddNewShow_AddedSuccessfully() {
        ShowsDataService showsDataService = new ShowsDataService(showsDataRepository);
        Show x = showsDataService.addShow("The Witcher 3", 5);
        assertNotNull(x, "Should 'not be null'");
        assertEquals(x.getTitle(), "The Witcher 3", "Should be 'The Witcher 3'");
        assertEquals(x.getReviews().get(0).getStarScore(), 5, "Should be '5' stars");
    }

    @Test
    void testAddDuplicateShow_ThrowsException() {
        ShowsDataService showsDataService = new ShowsDataService(showsDataRepository);
        showsDataService.addShow("The Witcher 2", 5);
        assertThrows(IllegalArgumentException.class,
                () -> showsDataService.addShow("The Witcher 2", 5),
                "Should throw 'IllegalArgumentException'"
        );
    }
}