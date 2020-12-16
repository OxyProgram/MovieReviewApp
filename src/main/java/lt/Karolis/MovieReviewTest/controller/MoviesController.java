package lt.Karolis.MovieReviewTest.controller;

import lt.Karolis.MovieReviewTest.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    private String apiKey = "530c56d37a8200c3cb27b16bcc2e444c";

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        Movie movie = restTemplate.getForObject(
                "https://api.themoviedb.org/3/movie/" + movieId  + "?api_key=" +apiKey,
                Movie.class);
        return movie;
    }

}
