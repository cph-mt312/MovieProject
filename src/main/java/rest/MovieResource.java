package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MovieDTO;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final MovieFacade FACADE = MovieFacade.getMovieFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("/count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieCount() {
        long count = FACADE.getMovieCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        List<MovieDTO> movieDTOs = new ArrayList<>();
        try {
            List<Movie> movies = FACADE.getAllMovies();
            for (Movie m : movies) {
                movieDTOs.add(new MovieDTO(m));
            }
            return new Gson().toJson(movieDTOs);
        } catch (Exception e) {
            return "An error occured";
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieById(@PathParam("id") int id) {
        MovieDTO movieDTO = new MovieDTO(FACADE.getMovieById(id));
        return new Gson().toJson(movieDTO);

    }

    @Path("/title/{title}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovieByTitle(@PathParam("title") String title) {
        List<Movie> movie = FACADE.getMovieByTitle(title);
        return GSON.toJson(movie);
    }
}
