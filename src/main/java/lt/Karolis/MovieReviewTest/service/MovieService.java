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

    public ArrayList<MovieJSON> fetchMovies(String email) {
        User user = userRepository.findByEmail(email);
        ArrayList<MovieJSON> list = new ArrayList<>();
        for(int i = 0; i < user.getNumberOfMovies(); i++) {
            MovieJSON movie = new MovieJSON();
            movie.setMovieID(user.getMovies().get(i).getMovieID());
            movie.setMovieTitle(user.getMovies().get(i).getMovieTitle());
            movie.setMovieReleased(user.getMovies().get(i).getMovieReleased());
            movie.setMovieOverview(user.getMovies().get(i).getMovieOverview());
            movie.setMovieThumbnail(user.getMovies().get(i).getMovieThumbnail());
            movie.setReview(user.getMovies().get(i).getReview());
            movie.setRating(user.getMovies().get(i).getRating());
            //movie.setReview(user.getMovieIDs().get(i).getReview());
            list.add(movie);
        }
        return list;
    }

    public boolean addMovie(AddMovieRequest request) {
        try {
            User user = userRepository.findByEmail(request.getEmail());
            for(Movie movie : user.getMovies()) {
                if(movie.getMovieID().equals(request.getMovieId()))
                    return false;
            }
            Movie movie = new Movie();
            movie.setMovieID(request.getMovieId());
            movie.setMovieTitle(request.getMovieTitle());
            movie.setMovieReleased(request.getMovieReleased());
            movie.setMovieOverview(request.getMovieOverview());
            movie.setMovieThumbnail(request.getMovieThumbnail());
            movie.setReview(request.getReview());
            movie.setRating(request.getRating());
            user.getMovies().add(movie);
            user.addNumberOfMovies();
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Exception adding a movie! Email: " + request.getEmail());
            return false;
        }
        return true;
    }

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
