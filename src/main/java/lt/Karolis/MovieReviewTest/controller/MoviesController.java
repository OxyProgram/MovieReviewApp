package lt.Karolis.MovieReviewTest.controller;

import lt.Karolis.MovieReviewTest.dto.AddMovieRequest;
import lt.Karolis.MovieReviewTest.dto.GetMoviesRequest;
import lt.Karolis.MovieReviewTest.dto.MovieJSON;
import lt.Karolis.MovieReviewTest.dto.SignupResponse;
import lt.Karolis.MovieReviewTest.service.MovieService;
import org.springframework.web.bind.annotation.*;

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
    public SignupResponse addMovie(@RequestBody AddMovieRequest request) {
        if(movieService.addMovie(request))
            return new SignupResponse(true);
        return new SignupResponse(false);
    }

}
