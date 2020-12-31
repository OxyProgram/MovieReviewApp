package lt.Karolis.MovieReviewTest.controller;

import lt.Karolis.MovieReviewTest.dto.*;
import lt.Karolis.MovieReviewTest.service.MovieService;
import lt.Karolis.MovieReviewTest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    private final MovieService movieService;


    public MoviesController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping("/getMovies")
    @PostMapping
    public ArrayList<MovieJSON> getMovieInfo(@RequestBody GetMoviesRequest request) {
        return movieService.fetchMovies(request.getEmail());
    }

    @RequestMapping("/addMovie")
    @PostMapping
    public SuccessResponse addMovie(@RequestBody AddMovieRequest request) {
        if(movieService.addMovie(request))
            return new SuccessResponse(true);
        return new SuccessResponse(false);
    }

}
