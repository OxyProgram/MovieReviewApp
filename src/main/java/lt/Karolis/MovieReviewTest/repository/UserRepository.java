package lt.Karolis.MovieReviewTest.repository;

import lt.Karolis.MovieReviewTest.model.User;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    void deleteByEmail(String email);
}
