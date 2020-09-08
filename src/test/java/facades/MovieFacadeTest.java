package facades;

import utils.EMF_Creator;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;

    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MovieFacade.getMovieFacade(emf);
        Movie m1 = facade.addMovie("Avatar", 2009, 7.8);
        Movie m2 = facade.addMovie("The Room", 2003, 3.7);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGetAllMovies() {
        int minSize = 2;
        List<Movie> movies = facade.getAllMovies();
        assert (movies.size() == minSize);
    }

    @Test
    public void testGetMovieById() {
        Movie movie = facade.getMovieById(1);
        assert (movie.getTitle().equalsIgnoreCase("Avatar"));
    }
}
