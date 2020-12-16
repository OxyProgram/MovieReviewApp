package lt.Karolis.MovieReviewTest.repository;

import lt.Karolis.MovieReviewTest.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
