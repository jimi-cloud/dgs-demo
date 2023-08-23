package com.jimicloud.dgs.dgsdemo;

import com.netflix.dgs.codegen.generated.types.Show;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ShowsDataFetcher.class})
class ShowsDataTest {

    @Test
    void testNullTitle_WithException() {
        ShowsDataFetcher showsDataFetcher = new ShowsDataFetcher();
        assertThrows(IllegalArgumentException.class,
                () -> showsDataFetcher.addShow(null, 10),
                "Should throw 'IllegalArgumentException'"
        );
    }

    @Test
    void testNullStarScore_WithException() {
        ShowsDataFetcher showsDataFetcher = new ShowsDataFetcher();
        assertThrows(IllegalArgumentException.class,
                () -> showsDataFetcher.addShow("Gamer", null),
                "Should throw 'IllegalArgumentException'"
        );
    }

    @Test
    void testShowsDataFetcher_ThreeShowsListed() {
        ShowsDataFetcher showsDataFetcher = new ShowsDataFetcher();
        List<Show> x = showsDataFetcher.shows(null);
        assertNotNull(x, "Should not be null");
        assertEquals(x.size(), 0, "Should be '0' shows listed");
    }

    @Test
    void testAddNewShow_AddedAndQueried() {
        ShowsDataFetcher showsDataFetcher = new ShowsDataFetcher();
        Show x = showsDataFetcher.addShow("The Witcher 4", 5);
        List<Show> shows = showsDataFetcher.shows("The Witcher 4");
        assertNotNull(x, "Should 'not be null'");
        assertNotNull(shows.get(0), "Should 'not be null'");
        assertEquals(shows.get(0).getTitle(), "The Witcher 4", "Should be 'The Witcher'");
        assertEquals(shows.get(0).getReviews().get(0).getStarScore(), 5, "Should be '5' stars");
    }

    @Test
    void testAddNewShow_AddedSuccessfully() {
        ShowsDataFetcher showsDataFetcher = new ShowsDataFetcher();
        Show x = showsDataFetcher.addShow("The Witcher 3", 5);
        assertNotNull(x, "Should 'not be null'");
        assertEquals(x.getTitle(), "The Witcher 3", "Should be 'The Witcher 3'");
        assertEquals(x.getReviews().get(0).getStarScore(), 5, "Should be '5' stars");
    }

    @Test
    void testAddDuplicateShow_ThrowsException() {
        ShowsDataFetcher showsDataFetcher = new ShowsDataFetcher();
        showsDataFetcher.addShow("The Witcher 2", 5);
        assertThrows(IllegalArgumentException.class,
                () -> showsDataFetcher.addShow("The Witcher 2", 5),
                "Should throw 'IllegalArgumentException'"
        );
    }
}