package lt.Karolis.MovieReviewTest.repository;

import lt.Karolis.MovieReviewTest.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    void deleteByEmail(String email);
}
