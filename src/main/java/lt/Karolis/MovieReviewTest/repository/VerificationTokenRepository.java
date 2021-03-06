package lt.Karolis.MovieReviewTest.repository;

import lt.Karolis.MovieReviewTest.model.User;
import lt.Karolis.MovieReviewTest.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
    void deleteByUser(User user);
}
