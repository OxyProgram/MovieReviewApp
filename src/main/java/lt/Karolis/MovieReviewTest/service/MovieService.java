package lt.Karolis.MovieReviewTest.service;

import lt.Karolis.MovieReviewTest.dto.AddMovieRequest;
import lt.Karolis.MovieReviewTest.dto.MovieJSON;
import lt.Karolis.MovieReviewTest.model.Movie;
import lt.Karolis.MovieReviewTest.model.User;
import lt.Karolis.MovieReviewTest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MovieService {

    private final UserRepository userRepository;

    public MovieService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
}
