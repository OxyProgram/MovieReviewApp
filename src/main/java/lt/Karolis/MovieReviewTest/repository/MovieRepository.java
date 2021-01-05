package lt.Karolis.MovieReviewTest.repository;

import lt.Karolis.MovieReviewTest.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findByMovieID(@Param("movie_id") String movieID);

}
