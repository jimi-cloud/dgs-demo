package com.jimicloud.dgs.dgsdemo;

import com.jimicloud.dgs.dgsdemo.repo.Show;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ShowsDataService.class})
class ShowsDataTest {

    @Autowired
    private final ShowsDataService showsDataService;

    ShowsDataTest(ShowsDataService showsDataService) {
        this.showsDataService = showsDataService;
    }


    @Test
    void testNullTitle_WithException() {
        assertThrows(IllegalArgumentException.class,
                () -> showsDataService.addShow(null, 10),
                "Should throw 'IllegalArgumentException'"
        );
    }

    @Test
    void testNullStarScore_WithException() {
        assertThrows(IllegalArgumentException.class,
                () -> showsDataService.addShow("Gamer", null),
                "Should throw 'IllegalArgumentException'"
        );
    }

    @Test
    void testShowsDataFetcher_ThreeShowsListed() {
        List<Show> x = showsDataService.fetchShowsWithOptionalFilter(null);
        assertNotNull(x, "Should not be null");
        assertEquals(x.size(), 0, "Should be '0' shows listed");
    }

    @Test
    void testAddNewShow_AddedAndQueried() {
        Show x = showsDataService.addShow("The Witcher 4", 5);
        List<Show> shows = showsDataService.fetchShowsWithOptionalFilter("The Witcher 4");
        assertNotNull(x, "Should 'not be null'");
        assertNotNull(shows.get(0), "Should 'not be null'");
        assertEquals(shows.get(0).getTitle(), "The Witcher 4", "Should be 'The Witcher'");
        assertEquals(shows.get(0).getReviews().get(0).starScore(), 5, "Should be '5' stars");
    }

    @Test
    void testAddNewShow_AddedSuccessfully() {
        Show x = showsDataService.addShow("The Witcher 3", 5);
        assertNotNull(x, "Should 'not be null'");
        assertEquals(x.getTitle(), "The Witcher 3", "Should be 'The Witcher 3'");
        assertEquals(x.getReviews().get(0).starScore(), 5, "Should be '5' stars");
    }

    @Test
    void testAddDuplicateShow_ThrowsException() {
        showsDataService.addShow("The Witcher 2", 5);
        assertThrows(IllegalArgumentException.class,
                () -> showsDataService.addShow("The Witcher 2", 5),
                "Should throw 'IllegalArgumentException'"
        );
    }
}