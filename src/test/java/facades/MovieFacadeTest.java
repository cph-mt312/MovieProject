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
    private static Movie m1 = new Movie("Pulp Fiction", 1994, 8.9);
    private static Movie m2 = new Movie("Alone in the Dark", 2005, 2.4);

    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MovieFacade.getMovieFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(m1);
            em.persist(m2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGetAllMovies() {
        List<Movie> movies = facade.getAllMovies();
        assertEquals(2, movies.size());
    }

    @Test
    public void testGetMovieById() {
        int id = m1.getId();
        Movie movie = facade.getMovieById(id);
        assertEquals(m1.getTitle(), movie.getTitle());
    }
    
    @Test
    public void testGetMovieByTitle() {
        String movieTitle = m1.getTitle();
        List<Movie> movies = facade.getMovieByTitle(movieTitle);
        assertEquals(movieTitle, movies.get(0).getTitle());
    }
}
