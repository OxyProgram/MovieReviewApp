package lt.Karolis.MovieReviewTest.service;

import lt.Karolis.MovieReviewTest.dto.AddMovieRequest;
import lt.Karolis.MovieReviewTest.dto.MovieJSON;
import lt.Karolis.MovieReviewTest.dto.MovieReviewsRequest;
import lt.Karolis.MovieReviewTest.model.Movie;
import lt.Karolis.MovieReviewTest.model.User;
import lt.Karolis.MovieReviewTest.repository.MovieRepository;
import lt.Karolis.MovieReviewTest.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public MovieService(UserRepository userRepository, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    public ArrayList<MovieJSON> fetchMovies(String email) {
        User user = userRepository.findByEmail(email);
        ArrayList<MovieJSON> list = new ArrayList<>();
        for(int i = 0; i < user.getNumberOfMovies(); i++) {
            MovieJSON movie = new MovieJSON();
            movie.setId(user.getMovieIDs().get(i).getId());
            movie.setMovieID(user.getMovieIDs().get(i).getMovieID());
            movie.setRating(user.getMovieIDs().get(i).getRating());
            movie.setReview(user.getMovieIDs().get(i).getReview());
            list.add(movie);
        }
        return list;
    }

    @Transactional
    public boolean addMovie(AddMovieRequest request) {
        try {
            User user = userRepository.findByEmail(request.getEmail());
            Movie movie = new Movie();
            movie.setMovieID(request.getMovieId());
            movie.setReview(request.getReview());
            movie.setRating(request.getRating());
            user.addMovie(movie);
            user.setNumberOfMovies();
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Exception adding a movie! Email: " + request.getEmail());
            return false;
        }
        return true;
    }

    @Transactional
    public List<MovieReviewsRequest> getReviews(String movieID) {
        List<Movie> movies = movieRepository.findByMovieID(movieID);
        if(movies.isEmpty())
            return null;
        int size = movies.size();
        List<MovieReviewsRequest> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            MovieReviewsRequest request = new MovieReviewsRequest();
            request.setUsername(movies.get(i).getUser().getUsername());
            request.setRating(movies.get(i).getRating());
            request.setReview(movies.get(i).getReview());
            list.add(request);
        }

        return list;
    }

}
