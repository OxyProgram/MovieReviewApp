package lt.Karolis.MovieReviewTest.controller;

import lt.Karolis.MovieReviewTest.dto.*;
import lt.Karolis.MovieReviewTest.service.MovieService;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping("/getReviews")
    @PostMapping
    public List<MovieReviewsRequest> getReviews(@RequestBody GetReviewsRequest request) {
        return movieService.getReviews(request.getMovieID());
    }


}
